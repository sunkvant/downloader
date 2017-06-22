package com.vamendrik.bootcamp.downloader.manager;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.vamendrik.bootcamp.downloader.model.Config;
import com.vamendrik.bootcamp.downloader.model.Link;

public class DownloaderManager {

	private int streamCount;

	private int countFiles;

	private ConcurrentLinkedQueue<Link> links;

	private static final Logger logger = Logger.getLogger(DownloaderManager.class);

	public DownloaderManager(Config config) {

		this.streamCount = config.getStreamCount();
		this.links = config.getLinks();
		this.countFiles = config.getLinks().size();

	}

	public long getSizeDownloaded() {

		return ThreadsHandler.getSizeDownloaded();

	}

	public int getCountDownloadedFiles() {

		return ThreadsHandler.getCountDownloadedFiles();

	}

	public int getCountFiles() {

		return countFiles;

	}

	public void start() {

		logger.info("Программа запущена в " + streamCount + " потоках.");

		Thread threads[] = new Thread[streamCount];

		for (int i = 0; i < threads.length; i++) {

			threads[i] = new Thread(new ThreadsHandler(links));

		}

		for (int i = 0; i < threads.length; i++) {

			threads[i].start();

		}

		for (int i = 0; i < threads.length; i++) {

			try {
				threads[i].join();
			} catch (InterruptedException e) {
				logger.error("Неизвестная ошибка. Программа прекратила работу!");
				System.exit(0);
			}

		}

	}

}
