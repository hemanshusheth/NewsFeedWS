package com.worldnewsonmap.tests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.worldnewsonmap.rss.controller.RSSFeedParser;
import com.worldnewsonmap.utils.URLHelper;
import com.worldnewsonmap.weather.controller.YahooRSSParser;
import com.worldnewsonmap.weather.model.Astronomy;
import com.worldnewsonmap.weather.model.Atmosphere;
import com.worldnewsonmap.weather.model.Channel;
import com.worldnewsonmap.weather.model.Forecast;
import com.worldnewsonmap.weather.model.Item;
import com.worldnewsonmap.weather.model.Location;
import com.worldnewsonmap.weather.model.Rss;
import com.worldnewsonmap.weather.model.Units;
import com.worldnewsonmap.weather.model.Wind;

import junit.framework.Assert;

public class YahooWeatherTest {
	private static final String yahooXml = "yahooWeatherQuery.xml";
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.
	 * forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where
	 * %20text%3D%22nome%2C%20ak%22)
	 * &format=json
	 * https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.
	 * forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where
	 * %20text="Boston%20MA")
	 */

	@Test
	public void test() {
		try {
			String xml = ReadWeatherFileToString();
			YahooRSSParser parser = new YahooRSSParser();
			Rss rss = parser.parse(xml);
			List<Channel> channels = rss.getChannels();
			for (Channel channel : channels) {
				PrintChannel(channel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String ReadWeatherFileToString() throws IOException {
		InputStream is = null;
		String xml = null;
		InputStreamReader reader = null;
		try {
			is = new FileInputStream(RSSFeedParser.class.getClassLoader().getResource(yahooXml).getFile());
			StringWriter writer = new StringWriter();
			reader = new InputStreamReader(is);
			IOUtils.copy(reader, writer);
			xml = writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
			is.close();
		}
		return xml;
	}
	
	@Test
	public void ReadUrlTest(){
		try{
			Rss rss = generateRSS("Boston MA");
			List<Channel> channels = rss.getChannels();
			assertEquals(channels.size(), 1);
			for (Channel channel : channels) {
				PrintChannel(channel);
			}
			rss = generateRSS("Paris France");
			channels = rss.getChannels();
			assertEquals(channels.size(), 1);
			for (Channel channel : channels) {
				PrintChannel(channel);
			}
			rss = generateRSS("Nasik MH India");
			channels = rss.getChannels();
			assertEquals(channels.size(), 1);
			for (Channel channel : channels) {
				PrintChannel(channel);
			}
			rss = generateRSS("Santiago Chile");
			channels = rss.getChannels();
			assertEquals(channels.size(), 1);
			for (Channel channel : channels) {
				PrintChannel(channel);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void descriptionTest() throws JAXBException, IOException{
		Rss rss = generateRSS("Boston MA");
		List<Channel> channels = rss.getChannels();
		assertEquals(channels.size(), 1);
		for (Channel channel : channels) {
			Item item = channel.getItem();
			String desc = item.getDescription();
			Document doc = Jsoup.parse(desc);
			Elements imgElements = doc.getElementsByTag("img");
			for (Element imgElement : imgElements) {
				String imgSrc = imgElement.attr("src");
				if (imgSrc.length() > 0) {
					System.out.println(imgSrc);
					break;
				}
			}
		}
	}
	
	private Rss generateRSS(String place) throws JAXBException, IOException{
		URL feed_url = URLHelper.buildYahooWeatherURL(place); 
		YahooRSSParser parser = new YahooRSSParser(feed_url);
		return parser.readFeed();
	}
	
	private void PrintChannel(Channel channel){
		System.out.println(channel.getTitle());
		System.out.println(channel.getLink());
		System.out.println(channel.getLanguage());
		System.out.println(channel.getDescription());
		System.out.println(channel.getLastBuildDate());
		System.out.println(channel.getLanguage());
		
		Location location = channel.getLocation();
		System.out.println(location.getCity());
		System.out.println(location.getCountry());
		System.out.println(location.getRegion());
		
		Units units = channel.getUnits();
		System.out.println(units.getDistance());
		System.out.println(units.getPressure());
		System.out.println(units.getSpeed());
		System.out.println(units.getTemperature());
		
		Wind wind = channel.getWind();
		System.out.println(wind.getSpeed());
		System.out.println(wind.getChill());
		System.out.println(wind.getDirection());
		
		Atmosphere atmosphere =channel.getAtmosphere(); 
		System.out.println(atmosphere.getHumidity());
		System.out.println(atmosphere.getPressure());
		System.out.println(atmosphere.getVisibility());
		System.out.println(atmosphere.getRising());
		
		Astronomy astronomy = channel.getAstronomy();
		System.out.println(astronomy.getSunrise());
		System.out.println(astronomy.getSunset());
		
		Item item = channel.getItem();
		System.out.println(item.toString());
		Forecast forecast = item.getForecast();
		System.out.println(forecast.toString());
	}
}
