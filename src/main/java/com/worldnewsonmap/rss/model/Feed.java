package com.worldnewsonmap.rss.model;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 */
public class Feed {
	private String title;
	private String link;
	private String language;
	private String copyright;
	private String pubDate;
	private String webmaster;
	private String lastbuilddate;
	private boolean isRegionalSupported;
	private List<FeedMessage> entries = new ArrayList<FeedMessage>();

	public Feed() {
	}

	public Feed(String title, String link, String language, String copyright, String pubDate,
			String webmaster,String lastbuilddate, boolean isRegionalSupported, 
			ArrayList<FeedMessage> entries) {
		this.title = title;
		this.link = link;
		this.language = language;
		this.copyright = copyright;
		this.pubDate = pubDate;
		this.entries = entries;
		this.lastbuilddate= lastbuilddate;
		this.webmaster =webmaster;
		this.isRegionalSupported = isRegionalSupported;
	}

	public List<FeedMessage> getMessages() {
		return entries;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getLanguage() {
		return language;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getPubDate() {
		return pubDate;
	}

	public String getWebmaster() {
		return webmaster;
	}
	
	public String getLastbuilddate() {
		return lastbuilddate;
	}
	
	@Override
	public String toString() {
		return "Feed [copyright=" + copyright + "," + " language=" + language + ", link=" + link + ", pubDate="
				+ pubDate +"webmaster="+webmaster+" lastbuilddate="+lastbuilddate+", title=" + title + "]";
	}

	public boolean getIsRegionalSupported() {
		return isRegionalSupported;
	}

	public void setIsRegionalSupported(boolean isRegionalSupported) {
		this.isRegionalSupported = isRegionalSupported;
	}
}
