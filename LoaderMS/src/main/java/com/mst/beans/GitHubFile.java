package com.mst.beans;

public class GitHubFile {
    private String name;
    private String path;
    private String sha;
    private String download_url; // This will hold the URL to download the file content
    private String type; // Can be 'file' or 'dir'
    private String content; // New field to store file content
    // Getters and setters
    public String getContent() {
        return content; // Getter for content
    }
    public void setContent(String content) {
        this.content = content; // Setter for content
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public String getDownload_url() {
		return download_url;
	}
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}
