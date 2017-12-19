package com.worldnewsonmap.tests;

import java.security.GeneralSecurityException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.worldnewsonmap.rss.controller.RSSFeedParser;
import com.worldnewsonmap.rss.model.Feed;
import com.worldnewsonmap.rss.model.FeedMessage;
import com.worldnewsonmap.utils.GoogleDomainResource;


public class TestClass {
	
	private static String geoUS= "&geo=San%20Francisco%20United%20States&scoring=n";
	private static String geoNonUS= "&geo=Paris%20France&scoring=n";
	private static String countryUrl = "&geo=Paris%20Île-de-France%20France&country=France";
	private static String pollutionUrl ="https://api.waqi.info/feed/geo:40.1;38.1/?";
	public static void main(String[] args){
		//invokeRestService(countryUrl);
		invokeRestServiceForPollution(pollutionUrl);
		//GoogleDomainResource r = new GoogleDomainResource();
		//testParser();
	}
	
	private static void testParser() {
		RSSFeedParser parser = new RSSFeedParser(geoUS);
		Feed feed;
		try {
			feed = parser.readFeed();
			printFeed(feed);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static void testParserNonUSA() {
		RSSFeedParser parser = new RSSFeedParser(geoUS);
		Feed feed;
		try {
			feed = parser.readFeed();
			printFeed(feed);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private static void invokeRestService(String url) {
		try 
		{
			ResteasyClient client = new ResteasyClientBuilder().build();
			String rest_base_url = "http://localhost:8080/NewsFeedWS/rest";
			String google_url = new String(url);
			 String final_url =  rest_base_url.concat("/json/").concat(google_url);
			 System.out.println(final_url);
			//Targeting the RESTful Webservice we want to invoke by capturing it in WebTarget instance.
			 Response response = client.target(final_url).request().get();
		     //Read output in string format
		     String value = response.readEntity(String.class);
		     System.out.println(value);
		        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void invokeRestServiceForPollution(String url) {
		try 
		{
			ResteasyClient client = new ResteasyClientBuilder().build();
			String tokenValue ="token=2723354b9da0539149dff367059989e400f71fec";
			String rest_base_url =url+ tokenValue;
			System.out.println(rest_base_url);
			Response response = client.target(rest_base_url).request().get();
		     //Read output in string format
		     String value = response.readEntity(String.class);
		     System.out.println(value);
		        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printFeed(Feed feed) {
	  System.out.println("Feed Title: " + feed.getTitle());
	  System.out.println("Feed Copyright: " + feed.getCopyright());
	  System.out.println("Feed Language: " + feed.getLanguage());
	  System.out.println("Feed Link: " + feed.getLink());
	  System.out.println("Feed Publisher Date: " + feed.getPubDate());
	  
	  System.out.println("FeedMessages: ");
	  for (FeedMessage message: feed.getMessages()) {
		System.out.println( "FeedMessage [headline=" + message.getHeadlines() + 
	    					", publisher="+message.getPublisher() + 
	    					", description=" + message.getDescription() +
	    					", image Source="+message.getImgSource() + 
	    					", link=" + message.getLink() + 
	    					", category=" + message.getCategory()+
	    					", guid=" + message.getGuid() +
	    					",pubData=" +message.getPubdate()+"]");
	  }
	}
}
