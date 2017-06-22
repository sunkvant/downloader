package com.vamendrik.bootcamp.downloader.manager;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.vamendrik.bootcamp.downloader.model.Link;

public class ThreadsHandler implements Runnable {
	
	private ConcurrentLinkedQueue<Link> links;
	
	private static int countDownloadedFiles;
	
	private static long sizeDownloaded;
	
	ThreadsHandler(ConcurrentLinkedQueue<Link> links) {
		
		this.links=links;
		
	}
	
	public static int getCountDownloadedFiles() {
		
		return countDownloadedFiles;
		
	}
	
	public static long getSizeDownloaded() {
		
		return sizeDownloaded;
		
	}


	public void run() {
		
		Downloader downloader=new Downloader();
		
		while(!links.isEmpty()) {
			
			long res=downloader.getFileFromUrl(links.poll());
			
			if (res>0) {
				
				countDownloadedFiles++;
				sizeDownloaded+=res;
				
			}
			
			
			
			
		}
		
		
	}

}
