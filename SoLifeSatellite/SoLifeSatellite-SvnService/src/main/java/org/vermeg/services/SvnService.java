package org.vermeg.services;

import java.util.List;
import java.util.Map;

import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.vermeg.entities.PackageByModule;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.entities.SvnCommit;

public interface SvnService {
	
	List<SvnCommit> getListOfCommit(String path, long startRevision, long endRevision);
	
	RepositoryTree getListOfModule(String path);
		
	PackageByModule getPackageByModule(String path, int i);
	
	List<PackageByModule> getListOfPackageByModule(String path);

	List<String> listOfPaths(Map<String, SVNLogEntryPath> changedPaths);
	
	List<String> listOfModule(String path);

}
