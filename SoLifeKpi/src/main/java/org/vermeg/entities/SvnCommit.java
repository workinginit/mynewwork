package org.vermeg.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "svn", type = "commit")
public class SvnCommit {
	
	private String author;
	@Id
	private long revision;
	private String message;
	private String date;
	private List<String> paths;
	
	public SvnCommit() {
		super();
	}	
	public SvnCommit(String author, long revision, String message, String date, List<String> paths) {
		super();
		this.author = author;
		this.revision = revision;
		this.message = message;
		this.date = date;
		this.paths = paths;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getRevision() {
		return revision;
	}
	public void setRevision(long revision) {
		this.revision = revision;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String> getPaths() {
		return paths;
	}
	public void setPaths(List<String> paths) {
		this.paths = paths;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
