package org.vermeg.services;

import java.util.List;

import org.vermeg.entities.PackageByModule;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.entities.SvnCommit;

public interface SvnService {
	
	List<SvnCommit> getListCommit(String path, long startRevision, long endRevision);
	
	RepositoryTree listEntries(String path);
		
	PackageByModule listEntries2(String path, int i);
	
	List<PackageByModule> listModule(String path);

}
