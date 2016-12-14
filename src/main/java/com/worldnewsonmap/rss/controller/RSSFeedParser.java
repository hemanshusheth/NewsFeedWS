package com.worldnewsonmap.rss.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.worldnewsonmap.rss.model.Feed;
import com.worldnewsonmap.rss.model.FeedMessage;
import com.worldnewsonmap.utils.URLHelper;

/*
 * Class to parse Google News RSS Feed
 */
public class RSSFeedParser {

	private static final String FEED_CHANNEL = "channel";
	private static final String FEED_LANGUAGE = "language";
	private static final String FEED_TITLE = "title";
	private static final String FEED_DESCRIPTION = "description";
	private static final String FEED_LINK = "link";
	private static final String FEED_WEBMASTER = "webmaster";
	private static final String FEED_PUB_DATE = "pubDate";
	private static final String FEED_LAST_BUILD_DATE = "lastBuildDate";
	private static final String FEED_COPYRIGHT = "copyright";
	private static final String ITEM = "item";
	private URL url;
	private static File xsd;
	private boolean isRegionalSupported= false;
	
	static {
		xsd = new File(RSSFeedParser.class.getClassLoader().
						getResource("googlenewsrss.xsd").getFile());
	}
	/*
	 * Constructor RSSFeedParser
	 * 
	 * @param - accepts the url link of the feed to be parsed
	 */
	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl.replaceAll(" ", "%20"));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * Reads the rss feed xml element by element to produce a Feed
	 */
	public Feed readFeed() throws Exception {
		Feed feed = new Feed();
		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		InputStream in = read();
		XMLEventReader eventReader = null;
		try {
			// Set header values initial to the empty string
			String language = "";
			String title = "";
			String link = "";
			String webmaster = "";
			String pubdate = "";
			String lastbuilddate = "";
			String copyright = "";
			FeedMessage feedMessage;
			ArrayList<FeedMessage> feedMessages = new ArrayList<FeedMessage>();

			eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					switch (localPart) {
					case FEED_TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case FEED_LINK:
						link = getCharacterData(event, eventReader);
						break;
					case FEED_WEBMASTER:
						webmaster = getCharacterData(event, eventReader);
						break;
					case FEED_PUB_DATE:
						pubdate = getCharacterData(event, eventReader);
						break;
					case FEED_LAST_BUILD_DATE:
						lastbuilddate = getCharacterData(event, eventReader);
						break;
					case FEED_LANGUAGE:
						language = getCharacterData(event, eventReader);
						break;
					case FEED_COPYRIGHT:
						copyright = getCharacterData(event, eventReader);
						break;
					case ITEM:
						feedMessage = new FeedMessage(event, eventReader);
						if (feedMessage != null)
							feedMessages.add(feedMessage);
						break;
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (FEED_CHANNEL)) {
						feed = new Feed(title, link, language, copyright, 
										pubdate,webmaster,lastbuilddate, isRegionalSupported, feedMessages);
						return feed;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		} finally {
			in.close();
			eventReader.close();
		}
		return null;
	}

	private String getCharacterData(XMLEvent event,XMLEventReader eventReader) 
											throws XMLStreamException {
		if (eventReader.peek().isCharacters())
			return eventReader.getElementText();
		return "";
	}

	private InputStream read() throws IOException {
		InputStream xml = null;
		InputStream xsdIns = new FileInputStream(xsd);
		URL newurl = new URL(url.toString());
		if(validateAgainstXSD(newurl, xsdIns)){
			xml = newurl.openStream();
			isRegionalSupported = true;
		}
		else
		{
			this.url = URLHelper.toDefaultURL(this.url);
			xml = url.openStream();
			isRegionalSupported = false;
		}
		return xml;		
	}
	
	public boolean validateAgainstXSD(URL url, InputStream xsd) 
												throws IOException
	{
		InputStreamReader insr = null;
		InputStreamReader xsdInsr = null;
	    try
	    { 
			InputStream in = url.openStream();
			insr = new InputStreamReader(in);
			xsdInsr = new InputStreamReader(xsd);
	    	SchemaFactory schemaFactory = SchemaFactory
	    		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    	Schema schema = schemaFactory.newSchema(new StreamSource(xsd));
	    	schema.newValidator().validate(new StreamSource(insr));
	        return true;
	    }
	    catch(Exception ex)
	    {
	        return false;
	    }
	    finally{
	    	insr.close();
	    	xsdInsr.close();
	    }
	}
}