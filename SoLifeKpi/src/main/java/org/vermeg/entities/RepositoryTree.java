package org.vermeg.entities;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "svnmodule", type = "module")
public class RepositoryTree {

	private String id;
	private String repositoryRoot;
	private long lastRevision;
	private List<String> module;	
	
	public RepositoryTree() {
		super();
	}	
	public RepositoryTree(String id, String repositoryRoot, long lastRevision, List<String> module) {
		super();
		this.id = id;
		this.repositoryRoot = repositoryRoot;
		this.lastRevision = lastRevision;
		this.module = module;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getModule() {
		return module;
	}
	public void setModule(List<String> module) {
		this.module = module;
	}
	public String getRepositoryRoot() {
		return repositoryRoot;
	}
	public void setRepositoryRoot(String repositoryRoot) {
		this.repositoryRoot = repositoryRoot;
	}
	public long getLastRevision() {
		return lastRevision;
	}
	public void setLastRevision(long lastRevision) {
		this.lastRevision = lastRevision;
	}
}
