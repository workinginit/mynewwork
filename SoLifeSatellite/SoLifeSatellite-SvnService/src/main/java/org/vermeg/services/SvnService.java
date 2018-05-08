package org.vermeg.services;

import java.util.List;
import java.util.Map;

import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.vermeg.entities.PackageByModule;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.entities.SvnCommit;

public interface SvnService {
	
	List<SvnCommit> getListCommit(String path, long startRevision, long endRevision);
	
	RepositoryTree listEntries(String path);
		
	PackageByModule listEntries2(String path, int i);
	
	List<PackageByModule> listModule(String path);

	List<String> listOfPaths(Map<String, SVNLogEntryPath> changedPaths);
	
	List<String> listOfModule(String path);

}
