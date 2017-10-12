package com.worldnewsonmap.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import com.worldnewsonmap.utils.GoogleDomainResource;

public class URLHelper {
	
	private final static String defaultCountry="United States";
	private final static String defaultGoogleHost="news.google.com";
	private static final String WEATHER_SERVICE_BASE_URL = "https://query.yahooapis.com/v1/public/yql?";
	
	public static String ParseURL(String url) throws MalformedURLException {
		url = StringUtils.stripAccents(url);
	    URL aURL = new URL(url);
	    String queryString = aURL.getQuery();
	    String domainName = getGoogleDomain(queryString);
	    url = url.replace(defaultGoogleHost, "news.google.".concat(domainName));
	    url = url.replace(" ","%20");
	    return url;
	  }
	
	public static String getGoogleDomain(String url) throws MalformedURLException{
		String domainName="";
		String[] queries = url.split("&");
		String country;
		if(queries.length > 1){
			country = queries[queries.length - 1];
			country = country.substring(country.indexOf("=") + 1).replace("%20", " ");
		}
		else
			country = defaultCountry;
		domainName = GoogleDomainResource.getDomainValue(country);
		return domainName;
	}
	/*
	 * Replace incoming url with the default Google URL 
	 * Domain names can be domain specific, default domain host is always news.google.com
	 */
	public static URL toDefaultURL(URL url) throws MalformedURLException {
		String urlHost = url.getHost();
		URL newUrl = new URL(url.toString().replace(urlHost.toString(), defaultGoogleHost));
		return newUrl;
	}
	
	public static URL buildYahooWeatherURL(String place) throws MalformedURLException {
		String urlString = yahooQueryBuilder(place);
		urlString = urlString.replace(" ","%20");
		URL aURL = new URL(urlString);
		return aURL;
	}
	
	private static String yahooQueryBuilder(String place){
		place = StringUtils.stripAccents(place);
		StringBuilder queryBuilder = new StringBuilder(WEATHER_SERVICE_BASE_URL);
		queryBuilder.append("q=")
					.append("select * from weather.forecast ")
					.append("where woeid in (")
									.append("select woeid from geo.places(1) ")
									.append("where text=")
									.append("\""+place+"\"")
									.append(")");
		return queryBuilder.toString();
	}
}
