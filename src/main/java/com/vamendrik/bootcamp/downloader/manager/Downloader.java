package com.vamendrik.bootcamp.downloader.manager;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.vamendrik.bootcamp.downloader.model.Link;

public class Downloader {

	private volatile URL url;
	private HttpURLConnection connection;
	private InputStream in;
	private FileOutputStream out;

	private static final Logger logger = Logger.getLogger(Downloader.class);

	public long getFileFromUrl(Link link) {

		try {
			url = new URL(link.getUrl());
			connection = (HttpURLConnection) url.openConnection();

			connection.connect();

			if (connection.getContentLength() == -1) {

				logger.error("Файл по ссылке [" + link.getUrl() + "] не найден!");
				return 0;

			}

			byte[] buf = new byte[8192];

			in = new BufferedInputStream(connection.getInputStream());

			out = new FileOutputStream(link.getFullPath());

			int reader = -1;
			long counter = 0;

			while ((reader = in.read(buf, 0, 8192)) != -1) {

				out.write(buf, 0, reader);
				counter += reader;

			}

			out.close();
			in.close();

			logger.info("Файл [" + link.getUrl() + "] был успешно загружен в [" + link.getFullPath() + "] Размер: "
					+ connection.getContentLength() / 1024 + " Kb");

			return counter;

		}

		catch (ConnectException e) {
			logger.error("Нет соединения с интернетом");
			System.exit(0);
			return 0;

		} catch (MalformedURLException e) {

			logger.error("Ссылка [" + link.getUrl() + "] не корректна!");
			return 0;

		} catch (UnknownHostException e) {

			logger.error("Не удалось найти [" + link.getUrl() + "]");
			return 0;
		}

		catch (Exception e) {

			logger.error("Неизвестная ошибка!");
			System.exit(0);
			return 0;

		}

	}

}
