package com.vamendrik.bootcamp.downloader.parser;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.vamendrik.bootcamp.downloader.model.Config;
import com.vamendrik.bootcamp.downloader.model.Link;

public class ParserArguments {

	private ConcurrentLinkedQueue<Link> links = new ConcurrentLinkedQueue<Link>();
	private String url;
	private String fileName;
	private File saveDirectory;
	private File sourceFile;
	private int streamCount=1;
	
	private static final Logger logger = Logger.getLogger(ParserArguments.class);
	

	private Config generateConfig() {

		if (sourceFile == null) {


			this.links.add(new Link(this.url,new File(this.saveDirectory.getPath()+"\\"+this.fileName)));

		} else {
			
			FileParser fileParser=new FileParser(this.sourceFile,this.saveDirectory);
			this.links=fileParser.getLinks();

		}

		return new Config(this.links, this.streamCount);

	}

	private boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Config Validate(String[] args) {

		String find = "";

		for (int i = 0; i < args.length; i++) {

			if (args[i].equals("-l") || args[i].equals("-p") || args[i].equals("-n") || args[i].equals("-f")
					|| args[i].equals("-t")) {

				find += args[i];

			}
		}

		if (((find.equals("-l-p-n")) && (args.length == 6)) || ((find.equals("-f-p-t")) && (args.length == 6))
				|| ((find.equals("-f-p")) && (args.length == 4))) {

			for (int i = 0; i < args.length; i++) {

				if (args[i].equals("-f")) {

					File file = new File(args[i + 1]);

					this.sourceFile = file;
					
					String[] pars = sourceFile.getName().split("[.]");

					String fileFormat = pars[pars.length - 1];
					
					if (!fileFormat.equals("csv") && !fileFormat.equals("json") && !fileFormat.equals("xml")) {

						logger.error("Неизвестный формат входного файла.");
						System.exit(0);

					}
					
					continue;

				}

				if (args[i].equals("-l")) {

					this.url = args[i + 1];
					continue;

				}

				if (args[i].equals("-p")) {

					File file = new File(args[i + 1]);

					if (!file.exists()) {

						logger.warn("Директория " + args[i + 1]
								+ " не существует. Программа попытается создать ее самостоятельно!");

						if (!file.mkdir()) {

							logger.error(
									"Невозможно создать директорию " + args[i + 1] + ". Возможно, у вас нет доступа!");
							System.exit(0);
						}

						logger.info("Директория " + args[i + 1] + " успешно создана!");

					}
					
					this.saveDirectory=file;

				}

				if (args[i].equals("-n")) {

					this.fileName = args[i + 1];
					continue;

				}

				if (args[i].equals("-t")) {

					if (!isNumber(args[i + 1])) {

						logger.error("В качестве аргумента для флага -t может быть только число!");
						System.exit(0);

					}

					if (Integer.parseInt(args[i + 1]) > 30) {

						logger.error("Указано слишком большое число потоков!");
						System.exit(0);

					}

					this.streamCount = Integer.parseInt(args[i + 1]);
					continue;

				}

			}

		} else {

			logger.error("Параметры указаны неверно!");
			System.exit(0);

		}
		return generateConfig();

	}

}
