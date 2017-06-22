package com.vamendrik.bootcamp.downloader.start;

import org.apache.log4j.Logger;

import com.vamendrik.bootcamp.downloader.manager.DownloaderManager;
import com.vamendrik.bootcamp.downloader.model.Config;
import com.vamendrik.bootcamp.downloader.parser.ParserArguments;

public class Start {

	private static final Logger logger = Logger.getLogger(Start.class);

	public static void main(String[] args) {

		ParserArguments parser = new ParserArguments();

		Config config = parser.Validate(args);

		if (config != null) {

			DownloaderManager downloaderManager = new DownloaderManager(config);

			downloaderManager.start();

			logger.info("-----------------------------------------------");
			logger.info("������� " + downloaderManager.getCountDownloadedFiles() + " �� "
					+ downloaderManager.getCountFiles() + " ������, ����� ������� " + downloaderManager.getSizeDownloaded()/1024+" ��.");

		}

	}

}
