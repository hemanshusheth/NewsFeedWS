package com.worldnewsonmap.weather.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.worldnewsonmap.weather.model.Channel;
import com.worldnewsonmap.weather.model.Rss;

public class YahooRSSParser {

	private Unmarshaller unmarshaller;
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	private Proxy proxy;
	private URL url;
	
	public YahooRSSParser() throws JAXBException {
		this.proxy = Proxy.NO_PROXY;
		CreateJAXBContext();
	}
	
	public YahooRSSParser(URL url) throws JAXBException {
		this.proxy = Proxy.NO_PROXY;
		this.url =url;
		CreateJAXBContext();
	}
	
	public YahooRSSParser(URL url, Proxy proxy) throws JAXBException{
		this.proxy = proxy;
		this.url = url;
		CreateJAXBContext();
	}

	private void CreateJAXBContext() throws JAXBException {
			JAXBContext context = JAXBContext.newInstance(Rss.class);
			unmarshaller = context.createUnmarshaller();
	}
	
	public Rss readFeed() throws IOException, JAXBException {
		String xml = retrieveRSS();
		Rss rss = parse(xml);
		return rss;
	}
	
	public Rss parse(String xml) throws JAXBException {
		return (Rss) unmarshaller.unmarshal(new StringReader(xml));
	}

	/**
	 * Open the connection to the service and retrieves the data.
	 * 
	 * @param serviceUrl
	 *            the service URL.
	 * @return the service response.
	 * @throws IOException
	 *             if an error occurs during the connection.
	 */
	private String retrieveRSS() throws IOException {
		if(url!=null){
			URLConnection connection = url.openConnection(proxy);
			InputStream is = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			StringWriter writer = new StringWriter();
			copy(reader, writer);
			reader.close();
			is.close();
			return writer.toString();
		}
		return "";
	}

	/**
	 * Copy the input reader into the output writer.
	 * 
	 * @param input
	 *            the input reader.
	 * @param output
	 *            the output writer.
	 * @return the number of char copied.
	 * @throws IOException
	 *             if an error occurs during the copy.
	 */
	private static long copy(Reader input, Writer output) throws IOException {
		char[] buffer = new char[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
}
