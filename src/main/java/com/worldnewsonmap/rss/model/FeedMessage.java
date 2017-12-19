package com.worldnewsonmap.rss.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/*
 * Represents one RSS message
 */
public class FeedMessage {

	private String title = "";
	private String headline = "";
	private String publisher = "";
	private String description = "";
	private String link = "";
	private String guid = "";
	private String pubdate = "";
	private String category = "";
	private String imgSource = "";
	private NewsItem newsItem;

	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String LINK = "link";
	static final String CATEGORY = "category";
	static final String PUBLICATION_DATE = "pubDate";
	static final String GUID = "guid";
	static final String ITEM = "item";

	/*
	 * Default constructor
	 */
	public FeedMessage() {
	}

	/*
	 * 
	 */
	public FeedMessage(XMLEvent event, XMLEventReader eventReader) {
		if (eventReader == null || event == null)
			return;
		try {
			while (eventReader.hasNext()) {
				event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName()
							.getLocalPart();
					switch (localPart) {
					case TITLE:
						title = getCharacterData(event, eventReader);
						if (title.contains(" - ")) {
							headline = title.substring(0, title.indexOf(" - "));
							publisher = title
									.substring(title.indexOf(" - ") + 2);
						}
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						String descriptionHTML = getCharacterData(event,
								eventReader);
						newsItem = new NewsItem(descriptionHTML);
						description = newsItem.getStory();
						imgSource = newsItem.getImgSource();
						break;
					case GUID:
						guid = getCharacterData(event, eventReader);
						break;
					case PUBLICATION_DATE:
						pubdate = getCharacterData(event, eventReader);
						break;
					case CATEGORY:
						category = getCharacterData(event, eventReader);
						break;
					}
				}
				else if (event.isEndElement()) {
					if (event.asEndElement().getName()
							.getLocalPart() == (ITEM)) {
						return;
					}
				}
			}
		}
		catch (XMLStreamException e) {
			// rss feed xml formation error
			System.out.println();
		}
	}

	private String getCharacterData(XMLEvent event,
			XMLEventReader eventReader) {
		try {
			return eventReader.getElementText();
		}
		catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public String getHeadlines() {
		return headline;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getDescription() {
		return description;
	}

	public String getLink() {
		return link;
	}

	public String getCategory() {
		return category;
	}

	public String getGuid() {
		return guid;
	}

	@SuppressWarnings("deprecation")
	public String getPubdate() {
		Date date = new Date(pubdate);
		SimpleDateFormat inputFormat = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss z", Locale.getDefault());
		pubdate = inputFormat.format(date);
		return pubdate;
	}

	public String getImgSource() {
		return imgSource;
	}

	@Override
	public String toString() {
		return "FeedMessage [headline=" + headline + ", publisher=" + publisher
				+ ", description=" + newsItem.getStory() + ", image Source="
				+ newsItem.getImgSource() + ", link=" + link + ", category="
				+ category + ", guid=" + guid + ",pubData=" + getPubdate()
				+ "]";
	}

	public boolean IsValidFeedMessage() {
		if (guid != null && guid.endsWith("cluster=0"))
			return false;
		return true;
	}
}