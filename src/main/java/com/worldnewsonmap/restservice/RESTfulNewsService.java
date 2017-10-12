package com.worldnewsonmap.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.worldnewsonmap.rss.controller.RSSFeedParser;
import com.worldnewsonmap.rss.model.Feed;
import com.worldnewsonmap.utils.Constants;
import com.worldnewsonmap.utils.ServiceLogger;
import com.worldnewsonmap.utils.URLHelper;

//Plain old Java Object it does not extend as class or implements 
//an interface

//The class registers its methods for the HTTP GET request using the @GET annotation. 
//Using the @Produces annotation, it defines that it can deliver several MIME types,
//text, XML and HTML. 

//The browser requests per default the HTML MIME type.
// 1 hello
// 2 text
// 3 xml
// 4 json
//Sets the path to base URL + /hello

@Path("/")
public class RESTfulNewsService {
	private Feed feed;
	private String GOOGLE_NEWS_URL = "https://news.google.com/news/section?output=rss";

	@GET
	@Path("/hello/{name}")
	public Response sayHello(@PathParam("name") String name) {
		String reflectName = "Welcome " + name + " to Google News Feed REST Service";
		return ReturnSuccess(reflectName);
	}

	// This method is called if plain text is request
	@GET
	@Path("/text/{text}")
	@Produces("text/plain")
	public Response getNewsFeed(@PathParam("text") String url) {
		String feed_url = GOOGLE_NEWS_URL.concat(url);
		try {
			url = URLHelper.ParseURL(feed_url);
			RSSFeedParser parser = new RSSFeedParser(url);
			feed = parser.readFeed();
		} catch (Exception e) {
			ReturnError(e.getMessage());
		}
		return ReturnSuccess(feed);
	}

	// This method is called if XML is request
	@GET
	@Path("/xml/{xml}")
	@Produces("application/xml")
	public Response getNewsFeedXML(@PathParam("xml") String url) {
		String feed_url = GOOGLE_NEWS_URL.concat(url);
		try {
			url = URLHelper.ParseURL(feed_url);
			RSSFeedParser parser = new RSSFeedParser(url);
			feed = parser.readFeed();
		} catch (Exception e) {
			ReturnError(e.getMessage());
		}
		return ReturnSuccess(feed);
	}

	// This method is called if XML is request
	@GET
	@Path("/json/{json}")
	@Produces("application/json")
	public Response getNewsFeedJSON(@PathParam("json") String url) {
		Response r = null;
		String feed_url = GOOGLE_NEWS_URL.concat(url);
		try {
			url = URLHelper.ParseURL(feed_url);
			RSSFeedParser parser = new RSSFeedParser(url);
			feed = parser.readFeed();
			r = ReturnSuccess(feed);
		} catch (Exception e) {
			ServiceLogger.Info(e.getMessage());
			ReturnError(e.getMessage());
		}
		return r;
	}

	@GET
	@Path("/verify")
	@Produces("application/text")
	public Response verifyRESTService() {
		String result = "NewsFeedRESTService Successfully started..";
		return ReturnSuccess(result);
	}

	// return HTTP response 200 in case of success
	private Response ReturnSuccess(Object Entity) {

		if (Entity instanceof Feed) {
			Feed feed = (Feed) Entity;
			ServiceLogger.Info(feed.toString());
		}

		return Response.status(200).entity(Entity).build();
	}

	@OPTIONS
	public Response myResource() {
		return Response.ok().build();
	}

	// return HTTP response 200 in case of success
	private Response ReturnError(Object Entity) {

		if (Entity instanceof Feed) {
			Feed feed = (Feed) Entity;
			ServiceLogger.Severe(feed.toString());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("'" + Entity.toString() + "'")
				.type(MediaType.TEXT_PLAIN).build();
	}
}