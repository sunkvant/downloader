package com.vamendrik.bootcamp.downloader.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vamendrik.bootcamp.downloader.model.Link;

public class FileParser {

	private File sourceFile;
	
	private File saveDirectory;

	private ConcurrentLinkedQueue<Link> links = new ConcurrentLinkedQueue<Link>();

	private static final Logger logger = Logger.getLogger(FileParser.class);

	public FileParser(File sourceFile,File saveDirectory) {

		this.sourceFile = sourceFile;
		this.saveDirectory=saveDirectory;

	}

	public ConcurrentLinkedQueue<Link> getLinks() {

		String[] pars = sourceFile.getName().split("[.]");
		String fileFormat = pars[pars.length - 1];
		
		
		if (fileFormat.equals("csv")) {

			CSVReader();

		}

		if (fileFormat.equals("json")) {

			JSONReader();

		}

		if (fileFormat.equals("xml")) {

			XMLReader();

		}
		return links;


	}

	public void JSONReader() {

		StringBuilder json = new StringBuilder();

		try {

			String line = "";

			BufferedReader br = new BufferedReader(new FileReader(this.sourceFile));

			while ((line = br.readLine()) != null) {

				json.append(line);

			}

			br.close();

		} catch (IOException e) {
			logger.error("Файл " + this.sourceFile.getPath() + " не найден или к нему нет доступа!");
			System.exit(0);

		}

		try {

			JSONObject obj = new JSONObject(json.toString());
			JSONArray array = obj.getJSONArray("links");

			for (int i = 0; i < array.length(); i++) {

				this.links.add(new Link(array.getJSONObject(i).getString("url"),
						new File(this.saveDirectory.getPath()+"\\"+array.getJSONObject(i).getString("filename"))));

			}

		} catch (JSONException e) {
			logger.error("JSON структура файла некорректна!");
			System.exit(0);
		}

	}

	public void CSVReader() {

		try {

			String line = "";

			BufferedReader br = new BufferedReader(new FileReader(this.sourceFile));

			while ((line = br.readLine()) != null) {

				String[] pars = line.split(" ");

				this.links.add(new Link(pars[0], new File(this.saveDirectory.getPath()+"\\"+pars[1])));

			}

			br.close();

		} catch (ArrayIndexOutOfBoundsException e) {

			logger.error("Неверная структура файла!");
			System.exit(0);
		}

		catch (IOException e) {
			logger.error("Файл " + this.sourceFile.getPath() + " не найден или к нему нет доступа!");
			System.exit(0);

		}

	}

	public void XMLReader() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(this.sourceFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("link");

			for (int i = 0; i < nList.getLength(); i++) {

				Node node = nList.item(i);
				Element elem = (Element) node;
				this.links.add(new Link(elem.getElementsByTagName("url").item(0).getTextContent(),
						new File(this.saveDirectory.getPath()+"\\"+elem.getElementsByTagName("filename").item(0).getTextContent())));
			}

		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}

		catch (SAXException e) {

			logger.error("XML струактура файла некорректна!");
			System.exit(0);

		} catch (IOException e) {

			logger.error("Файл " + this.sourceFile.getPath() + " не найден или к нему нет доступа!");
			System.exit(0);

		}

	}

}
