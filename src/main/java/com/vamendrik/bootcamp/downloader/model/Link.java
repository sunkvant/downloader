package com.vamendrik.bootcamp.downloader.model;

import java.io.File;

public class Link {
	
	private String url;
	private File fullPath;
	
	public Link(String url,File fullPath) {
		
		this.url=url;
		this.fullPath=fullPath;
		
	}

	
	public String getUrl() {
		return url;
	}
	
	public File getFullPath() {
		return fullPath;
	}

}
