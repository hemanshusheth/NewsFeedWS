package com.worldnewsonmap.restservice;

import java.io.IOException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import com.worldnewsonmap.weather.model.Feed;
import com.worldnewsonmap.weather.model.Rss;
import com.worldnewsonmap.rss.controller.RSSFeedParser;
import com.worldnewsonmap.utils.ServiceLogger;
import com.worldnewsonmap.utils.URLHelper;
import com.worldnewsonmap.weather.controller.YahooRSSParser;

@Path("/weather")
public class RESTfulWeatherService {
	private YahooRSSParser parser;
	private Rss rss;
	
	// This method is called if json is request
	@GET
	@Path("/hello/{text}")
	public Response sayHello(@PathParam("text") String name) {
		String reflectName ="Welcome " + name + " to Yahoo Weather REST Service";
		return ReturnSuccess(reflectName);
	}
	
	// This method is called if json is request
		@GET
		@Path("/json/{text}")
		@Produces("application/json")
		public Response getWeather(@PathParam("text") String place) {
			Feed feed = null;
			try {
				URL feed_url = URLHelper.buildYahooWeatherURL(place); 
				YahooRSSParser parser = new YahooRSSParser(feed_url);
				rss = parser.readFeed();
				feed = new Feed(rss);
			} catch (JAXBException | IOException e) {
				ReturnError(e.getMessage());
			}
			return ReturnSuccess(feed);
		}
	
	// return HTTP response 200 in case of success
	private Response ReturnSuccess(Object Entity){
		if(Entity instanceof Feed){
			Feed feed = (Feed) Entity;
			ServiceLogger.Info(rss.toString());
		}
		
		return Response.status(200).entity(Entity)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", " DELETE, HEAD, GET, OPTIONS, POST, PUT")
				.header("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition, Content-Description")
				.header("Access-Control-Max-Age", "1728000").build();
	}
		
	// return HTTP response 200 in case of success
	private Response ReturnError(Object Entity){
		if(Entity instanceof Rss){
			Feed feed = (Feed) Entity;
			//ServiceLogger.Severe(rss.toString());
		}
		return Response.status(Response.Status.BAD_REQUEST)
	                .entity("'" + Entity.toString() + "'")
	                .type(MediaType.TEXT_PLAIN)
	                .build();
		}
}