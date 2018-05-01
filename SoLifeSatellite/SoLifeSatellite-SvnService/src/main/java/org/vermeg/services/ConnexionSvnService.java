package org.vermeg.services;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;


public class ConnexionSvnService {

	private static SVNRepository svnRepository = null;

	private ConnexionSvnService(String url, String userName, String password) {
		DAVRepositoryFactory.setup();
		
		try {
			 svnRepository = SVNRepositoryFactory.create( SVNURL.parseURIEncoded(url) );
			 @SuppressWarnings("deprecation")
			 ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(userName, password);
		     svnRepository.setAuthenticationManager( authManager );
		} catch (SVNException e) {
			e.printStackTrace();
		}
	}
	
	public static SVNRepository getInstance(String url, String userName, String password) {
		if (svnRepository==null) 
		{
			new ConnexionSvnService(url,userName,password);			
		}
		   return svnRepository;
	}
    		
}