package org.vermeg.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tmatesoft.svn.core.SVNException;
import org.vermeg.entities.PackageByModule;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.entities.SvnCommit;
import org.vermeg.services.SvnService;

@RestController
@CrossOrigin("*")
public class SvnCommitRestService {
	
	@Autowired
	private SvnService svnservice;

	//startRevision = 1 & endRevision = -1 & path =""
	@RequestMapping(value="/allcommit", method=RequestMethod.GET)
	public List<SvnCommit> getListCommit(@PathParam("startRevision") long startRevision,@PathParam("endRevision") long endRevision, @PathParam("path") String path) throws SVNException{
      
		return svnservice.getListOfCommit(path, startRevision, endRevision);
	}
	
	@RequestMapping(value="/getModule", method=RequestMethod.GET)
	public RepositoryTree getModule(@PathParam("path") String path){
      
		return svnservice.getListOfModule(path);
	}
	
	@RequestMapping(value="/getPackageByModule", method=RequestMethod.GET)
	public List<PackageByModule> getPackage(@PathParam("path") String path){
      
		return svnservice.getListOfPackageByModule(path);
	}
		
}
