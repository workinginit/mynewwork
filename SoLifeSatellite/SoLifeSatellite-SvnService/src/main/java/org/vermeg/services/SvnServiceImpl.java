package org.vermeg.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
	
	@Value("${repository.url:https://svn.riouxsvn.com/modulerepo}")
    private String url;
	
    @Value("${repository.userName}")
    private String userName;
    
    @Value("${repository.password}")
    private String password;
    
	@Override
	public List<SvnCommit> getListCommit(String patho, long startRevision, long endRevision) {
		ArrayList<SvnCommit> listofcommit = new ArrayList<SvnCommit>();
		String[] array = {patho};          

		SVNRepository repository = ConnexionSvnService.getInstance(url, userName, password);
        Collection<?> logEntries = null;

        try {
			logEntries = repository.log(array , null , startRevision , endRevision , true , true );
		} catch (SVNException e) {
			e.printStackTrace();
		}

        for ( Iterator<?> entries = logEntries.iterator( ); entries.hasNext( ); ) {
            SVNLogEntry logEntry = ( SVNLogEntry ) entries.next( );
    	
            	SvnCommit s = new SvnCommit();
            	s.setAuthor(logEntry.getAuthor());
            	s.setRevision(logEntry.getRevision());
            	s.setMessage(logEntry.getMessage());
            	s.setDate(DateFormatUtils.format(logEntry.getDate(), "yyyy-MM-dd"));
            	
		            if ( logEntry.getChangedPaths( ).size( ) > 0 ) {
		            	
		            	ArrayList<String> paths = new ArrayList<String>();
		                Set<?> changedPathsSet = logEntry.getChangedPaths( ).keySet( );
		
		                for ( Iterator<?> changedPaths = changedPathsSet.iterator( ); changedPaths.hasNext( ); ) {
		                	
		                    SVNLogEntryPath entryPath = ( SVNLogEntryPath ) logEntry.getChangedPaths( ).get( changedPaths.next( ) );	                    
		                    paths.add(entryPath.getPath().substring(1));
		                    
		                }
	                    s.setPaths(paths);
		            }    
               listofcommit.add(s);			                   
            }
        
		repository.closeSession();     
		return listofcommit;
	}

	@SuppressWarnings("deprecation")
	@Override
	public RepositoryTree listEntries(String pathDir) {
        ArrayList<String> listTree = new ArrayList<>();
        RepositoryTree rt = new RepositoryTree();
		SVNRepository repository = ConnexionSvnService.getInstance(url, userName, password);

		try {
			Collection<?> entries = repository.getDir(pathDir, -1, null, (Collection<?>) null);
	        Iterator<?> iterator = entries.iterator();

	        while (iterator.hasNext()) {
	            SVNDirEntry entry = (SVNDirEntry) iterator.next();

	            if( (entry.getKind() == SVNNodeKind.DIR) && !entry.getName().equals("src") && !entry.getName().equals(".settings")) {
	            	  String t = entry.getName();
	            	  listTree.add(t);
	            }
	        
	        }

	        rt.setId("1000");
	        rt.setModule(listTree);
	        rt.setLastRevision(repository.getLatestRevision());
	        rt.setRepositoryRoot(repository.getRepositoryRoot().toString());

		} catch (SVNException e) {
			e.printStackTrace();
		}
        
		return rt;
	}
	
	private HashSet<String> set = new HashSet<String>(); 
	
	@Override
	public PackageByModule listEntries2(String path, int i) {
		SVNRepository repository = ConnexionSvnService.getInstance(url, userName, password);	
		
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
			        set.add( (test1.substring(test1.indexOf("/java")+6,test1.lastIndexOf("/") )).replaceAll("/", "."));
		        }
  
		        listEntries2( (path.equals("")) ? entry.getName() : path + "/" + entry.getName(),1);
	        }

		} catch (SVNException e) {
			e.printStackTrace();
		}	
		
		PackageByModule pbm = new PackageByModule(path.substring(0, path.indexOf("/src/main/java")),set);

		return pbm;
	}
	
	@Override
	public List<PackageByModule> listModule(String path) {
		SVNRepository repository = ConnexionSvnService.getInstance(url, userName, password);
        ArrayList<String> listModule = new ArrayList<>();
        ArrayList<PackageByModule> listPackByModule = new ArrayList<>();

		try {
			Collection<?> entries = repository.getDir(path, -1, null, (Collection<?>) null);
	        Iterator<?> iterator = entries.iterator();

	        while (iterator.hasNext()) {
	            SVNDirEntry entry = (SVNDirEntry) iterator.next();
	            if(entry.getKind() == SVNNodeKind.DIR) {
	            	if(!entry.getName().equals("src") && !entry.getName().equals(".settings") ) {
		            	listModule.add(entry.getName());
	            	}
	            }
	        }

		} catch (SVNException e) {
			e.printStackTrace();
		}	
		
		for(String module : listModule) {
			listPackByModule.add(listEntries2(module + "/src/main/java", 0));
		}
			
	
	return listPackByModule;
	}
	

}
