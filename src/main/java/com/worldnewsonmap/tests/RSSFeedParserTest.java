package com.worldnewsonmap.tests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.translate.Translate;
import com.google.api.services.translate.Translate.Languages;
import com.google.api.services.translate.Translate.Translations;
import com.worldnewsonmap.rss.controller.RSSFeedParser;
import com.worldnewsonmap.rss.model.Feed;

public class RSSFeedParserTest {
	private RSSFeedParser parser;
	private String GOOGLE_NEWS_URL ="https://news.google.com/news/section?output=rss";
	private String GOOGLE_NEWS_URL_DOMAIN = "https://news.google.cl/news/section?output=rss";
	private String GOOGLE_NEWS_URL_DOMAIN_INDIA = "https://news.google.co.in/news/section?output=rss";
	private String rest_base_url ="http://localhost:8080/NewsFeedWS/rest";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRSSFeedParser() throws IOException, XMLStreamException {
		}

	@Test
	public void testReadFeed() throws Exception {
		String url = GOOGLE_NEWS_URL+"&geo=San%20Francisco";
		parser= new RSSFeedParser(url);
		Feed feed = parser.readFeed();
	}
	@Test
	public void testReadFeedNonUS() throws Exception {
		String url = GOOGLE_NEWS_URL+"&geo=Paris%20Île-de-France%20France";
		parser= new RSSFeedParser(url);
		Feed feed = parser.readFeed();
	}
	
	@Test
	public void testEnglishTranslation() throws IOException, XMLStreamException {
		String noneng = "&geo=Québec%20City%20Québec%20Canada";
		String eng = StringUtils.stripAccents(noneng);
		assertEquals(eng, "&geo=Quebec%20City%20Quebec%20Canada");
	}
	
	@Test
	public void testCountryDomain(){
		String resturl = "&geo=Paris%20Île-de-France%20France&country=France";
		
	}
	@Test
	public void readXSD() throws IOException{ 
		InputStream xsd = new FileInputStream(RSSFeedParser.class.getClassLoader().getResource("googlenewsrss.xsd").getFile());
		String urlString = GOOGLE_NEWS_URL+"&geo=Paris%20Ile-de-France%20France";
		URL url = new URL(urlString);
		RSSFeedParser parser = new RSSFeedParser(urlString);
		boolean actual = parser.validateAgainstXSD(url, xsd);
		assertEquals(true, actual);
	}
	
	@Test
	public void validateAgainstXSDDomainTest() throws Exception{
		InputStream xsd = new FileInputStream(RSSFeedParser.class.getClassLoader().getResource("googlenewsrss.xsd").getFile());
		String urlString = GOOGLE_NEWS_URL_DOMAIN+"&geo=Santiago%20Chile";
		URL url = new URL(urlString);
		RSSFeedParser parser = new RSSFeedParser(urlString);
		boolean actual = parser.validateAgainstXSD(url, xsd);
		assertEquals(false, actual);
	}
	
	@Test
	public void validateAgainstXSDTest() throws Exception{
		InputStream xsd = new FileInputStream(RSSFeedParser.class.getClassLoader().getResource("googlenewsrss.xsd").getFile());
		String urlString = GOOGLE_NEWS_URL+"&geo=Santiago%20Chile";
		URL url = new URL(urlString);
		RSSFeedParser parser = new RSSFeedParser(urlString);
		boolean actual = parser.validateAgainstXSD(url, xsd);
		assertEquals(true, actual);
		
		urlString = GOOGLE_NEWS_URL_DOMAIN_INDIA+"&geo=Mumbai%20India";
		url = new URL(urlString);
		parser = new RSSFeedParser(urlString);
		actual = parser.validateAgainstXSD(url, xsd);
		assertEquals(true, actual);
	}
}
