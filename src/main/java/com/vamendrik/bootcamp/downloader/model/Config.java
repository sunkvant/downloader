package com.vamendrik.bootcamp.downloader.model;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Config {

	private ConcurrentLinkedQueue<Link> links;
	private int streamCount;
	
	public Config(ConcurrentLinkedQueue<Link> links,int streamCount) {
		
		this.links=links; 
		this.streamCount=streamCount;
		
		
	}

	public ConcurrentLinkedQueue<Link> getLinks() {
		return links;
	}

	public int getStreamCount() {
		return streamCount;
	}

}
