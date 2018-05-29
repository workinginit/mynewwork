package org.vermeg.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.vermeg.entities.PackageByModule;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.entities.SvnCommit;

@Service
public class SvnServiceImpl implements SvnService {
	
	@Value("${repository.url}")
    private String url;
	
    @Value("${repository.userName}")
    private String userName;
    
    @Value("${repository.password}")
    private String password;
    
	private HashSet<String> set = new HashSet<String>(); 
    
    /**
     * cette méthode permet de retourner la lise de tous les revisions elle prend comme paramètre :
     * patho : url de la branche du projet
     * startRevision : à partir de quelle revision on commence
     * endRevision : jusqu'a quelle revision on s'arrete
     * Checked
     */
	@Override
	public List<SvnCommit> getListOfCommit(String patho, long startRevision, long endRevision) {
		ArrayList<SvnCommit> listofcommit = new ArrayList<SvnCommit>();
		String[] array = {patho};          

		SVNRepository repository = ConnexionSvnService.getInstance(url, userName, decrypt(password));
        Collection<?> logEntries = null;

        try {
			logEntries = repository.log(array , null , startRevision , endRevision , true , true );
		} catch (SVNException e) {
			e.printStackTrace();
		}

        for ( Iterator<?> entries = logEntries.iterator( ); entries.hasNext( ); ) {
            
	        	SVNLogEntry logEntry = ( SVNLogEntry ) entries.next( );
	            SvnCommit s = new SvnCommit(logEntry.getAuthor(), logEntry.getRevision(), logEntry.getMessage(), DateFormatUtils.format(logEntry.getDate(), "yyyy-MM-dd")
	            			, logEntry.getChangedPaths( ).size( ) > 0 ? listOfPaths(logEntry.getChangedPaths( )) : null);
		 	            
	            listofcommit.add(s);			                   
            }
        
		repository.closeSession();     
		return listofcommit;
	}
	
	
	/**
	 * cette méthode permet de retourner les modules à partir d'un URL, elle prend comme paramètre :
	 * pathDir : url de la branche du projet
	 * Checked 
	 */
	@SuppressWarnings("deprecation")
	@Override
	public RepositoryTree getListOfModule(String pathDir) {
        RepositoryTree rt = new RepositoryTree();
		SVNRepository repository = ConnexionSvnService.getInstance(url, userName, decrypt(password));

		try {
	        rt.setId("1000");
	        rt.setModule(listOfModule(pathDir));
	        rt.setLastRevision(repository.getLatestRevision());
	        rt.setRepositoryRoot(repository.getRepositoryRoot().toString());

		} catch (SVNException e) {
			e.printStackTrace();
		}
        
		return rt;
	}
	
	/**
	 * cette méthode permet de retourner la liste des packages pour un module elle prend comme paramètre:
	 * path :  url de la branche du projet
	 * i : a chaque recherche des packages par module i prendra comme valeur 0
	 * Checked
	 */
	@Override
	public PackageByModule getPackageByModule(String path, int i) {
		SVNRepository repository = ConnexionSvnService.getInstance(url, userName, decrypt(password));	
		
		if(i == 0) {
			set = new HashSet<String>(); 
		}
		
		try {		
			Collection<?> entries = repository.getDir(path, -1, null, (Collection<?>) null);
	        Iterator<?> iterator = entries.iterator();

	        while (iterator.hasNext()) {	
	            SVNDirEntry entry = (SVNDirEntry) iterator.next();    
	            
		       if (entry.getKind() == SVNNodeKind.FILE ) {
		        	String test1 =  (path.equals("") ? "" : path + "/") + entry.getName();
			        if(test1.contains("/src/main/java/")) {
				        set.add( (test1.substring(test1.indexOf("/java")+6,test1.lastIndexOf("/") )).replaceAll("/", "."));
			        }		        
		        }
		        
  
		        getPackageByModule( (path.equals("")) ? entry.getName() : path + "/" + entry.getName(),1);
	        }

		} catch (SVNException e) {
			e.printStackTrace();
		}	
		
		PackageByModule pbm = new PackageByModule(path,set);

		return pbm;
	}
	
	
	/**
	 * Etape 1
	 * cette méthode permet de retourner la liste des packages pour un module elle prend comme paramètre:
	 * path :  url de la branche du projet
	 * Checked
	 */
	@Override
	public List<PackageByModule> getListOfPackageByModule(String path) {
        ArrayList<PackageByModule> listPackByModule = new ArrayList<>();

		for(String module : listOfModule(path)) {
			listPackByModule.add(getPackageByModule(module, 0));
		}
			
		return listPackByModule;
	}

	/**
	 * get paths
	 * Checked
	 */
	@Override
	public List<String> listOfPaths(Map<String, SVNLogEntryPath> changedPathVariable) {	
        ArrayList<String> paths = new ArrayList<String>();
        Set<?> changedPathsSet = changedPathVariable.keySet( );

        for ( Iterator<?> changedPaths = changedPathsSet.iterator( ); changedPaths.hasNext( ); ) {
             SVNLogEntryPath entryPath = ( SVNLogEntryPath ) changedPathVariable.get( changedPaths.next( ) );	                    
             paths.add(entryPath.getPath().substring(1));    
        }
      
		return paths;
	}

	/**
	 * cette méthode est utilisé dans d'autre méthode afin de récuperer la liste des modules d'un project
	 * path :  url de la branche du projet
	 * Checked
	 */
	@Override
	public List<String> listOfModule(String path) {
		SVNRepository repository = ConnexionSvnService.getInstance(url, userName, decrypt(password));
        ArrayList<String> listModule = new ArrayList<>();

		try {
			Collection<?> entries = repository.getDir(path, -1, null, (Collection<?>) null);
	        Iterator<?> iterator = entries.iterator();

	        while (iterator.hasNext()) {
	            SVNDirEntry entry = (SVNDirEntry) iterator.next();

	            if( (entry.getKind() == SVNNodeKind.DIR) && !entry.getName().equals("src") && !entry.getName().equals(".settings")) {
	            	  String t = entry.getName();
	            	  listModule.add(t);
	            }
	        }
		} catch (SVNException e) {
			e.printStackTrace();
		}		
		
		return listModule;
	}
	
	 public String decrypt(String password){
	        String aCrypter= "";
	        for (int i=0; i<password.length();i++)  {
	            int c=password.charAt(i)^48;  
	            aCrypter=aCrypter+(char)c; 
	        }
	        return aCrypter;
	    }

}
