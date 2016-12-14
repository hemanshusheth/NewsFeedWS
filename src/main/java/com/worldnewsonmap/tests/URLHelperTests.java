/**
 * 
 */
package com.worldnewsonmap.tests;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.worldnewsonmap.utils.GoogleDomainResource;
import com.worldnewsonmap.utils.URLHelper;
import com.worldnewsonmap.weather.controller.YahooRSSParser;

/**
 * @author HEMANSHU
 *
 */
public class URLHelperTests {

	private String GOOGLE_NEWS_URL = "https://news.google.com/news/section?output=rss";
	private String GOOGLE_NEWS_URL_DOMAIN = "https://news.google.cl/news/section?output=rss";
	private final String domainResourceFile = "resources\\googleDomains.xml";
	private GoogleDomainResource gdr;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String fileName = domainResourceFile;
		gdr= new GoogleDomainResource();
		gdr.Initialize(fileName);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.worldnewsonmap.utils.URLHelper#ParseURL(java.lang.String)}.
	 * @throws MalformedURLException 
	 */
	@Test
	public void testParseURL() throws MalformedURLException {
		String queryString = "&geo=Paris%20Île-de-France%20France&country=France";
		String url = GOOGLE_NEWS_URL.concat(queryString);
		String expected = URLHelper.ParseURL(url);
		assertEquals(expected,
					"https://news.google.fr/news/section?output=rss&geo=Paris%20Ile-de-France%20France&country=France");
	}
	
	@Test
	public void toDefaultURLtest() throws MalformedURLException {
		String queryString = "&geo=Paris%20Ile-de-France%20France&country=France";
		String url = GOOGLE_NEWS_URL_DOMAIN.concat(queryString);
		URL expectedURL = URLHelper.toDefaultURL(new URL(url));
		String expected = expectedURL.toString();
		assertEquals(expected,GOOGLE_NEWS_URL.concat(queryString));}

	/**
	 * Test method for {@link com.worldnewsonmap.utils.URLHelper#getGoogleDomain(java.lang.String)}.
	 * @throws MalformedURLException 
	 */
	@Test
	public void testGetGoogleDomain() throws MalformedURLException {
		String queryString = "&geo=London%20UnitedKingdom&country=United%20Kingdom";
		String expected = URLHelper.getGoogleDomain(queryString);
		assertEquals("co.uk", expected);
	}
	
	@Test
	public void buildYahooWeatherURLTest(){
		try {
			URL url = URLHelper.buildYahooWeatherURL("Boston MA USA");
			System.out.println(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
