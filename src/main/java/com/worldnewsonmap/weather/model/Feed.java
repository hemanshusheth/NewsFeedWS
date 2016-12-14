/**
 * 
 */
package com.worldnewsonmap.weather.model;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author HEMANSHU
 *
 */
public class Feed {
	private String title;
	private String link;
	private String pubDate;
	private String imageSource;
	private int CurrentTemp;
	private Wind wind;
	private Atmosphere atmosphere;
	private Astronomy astronomy;
	private Forecast forecasts;
	private Units units;
	
	public Feed(Rss rss){
		List<Channel> channels = rss.getChannels();
		Channel channel = channels.get(0);
		setTitle(channel.getTitle());
		setLink(channel.getLink());
		Item item = channel.getItem();
		setPubDate(item.getPubDate());
		
		String desc = item.getDescription();
		Document doc = Jsoup.parse(desc);
		Elements imgElements = doc.getElementsByTag("img");
		for (Element imgElement : imgElements) {
			String imgSrc = imgElement.attr("src");
			if (imgSrc.length() > 0) {
				setImageSource(imgSrc);
				break;
			}		
		}
		setWind(channel.getWind());
		setAtmosphere(channel.getAtmosphere());
		setAstronomy(channel.getAstronomy());
		setUnits(channel.getUnits());
		Condition condition= item.getCondition();
		setCurrentTemp(condition.getTemp());
		Forecast forecasts = item.getForecast();
		setForecasts(forecasts);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title.substring(title.indexOf('-') + 1);
	}

	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the imageSource
	 */
	public String getImageSource() {
		return imageSource;
	}

	/**
	 * @param imageSource the imageSource to set
	 */
	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	/**
	 * @return the currentTemp
	 */
	public int getCurrentTemp() {
		return CurrentTemp;
	}

	/**
	 * @param currentTemp the currentTemp to set
	 */
	public void setCurrentTemp(int currentTemp) {
		CurrentTemp = currentTemp;
	}

	/**
	 * @return the wind
	 */
	public Wind getWind() {
		return wind;
	}

	/**
	 * @param wind the wind to set
	 */
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	
	/**
	 * @return sets unit for each data 
	 */
	public Units getUnits() {
		return units;
	}
	
	/**
	 * @param sets unit for each data 
	 */
	private void setUnits(Units units) {
		this.units = units;
	}
	
	/**
	 * @return the atmosphere
	 */
	public Atmosphere getAtmosphere() {
		return atmosphere;
	}

	/**
	 * @param atmosphere the atmosphere to set
	 */
	public void setAtmosphere(Atmosphere atmosphere) {
		this.atmosphere = atmosphere;
	}

	/**
	 * @return the astronomy
	 */
	public Astronomy getAstronomy() {
		return astronomy;
	}

	/**
	 * @param astronomy the astronomy to set
	 */
	public void setAstronomy(Astronomy astronomy) {
		this.astronomy = astronomy;
	}

	/**
	 * @return the forecasts
	 */
	public Forecast getForecasts() {
		return forecasts;
	}

	/**
	 * @param forecasts the forecasts to set
	 */
	public void setForecasts(Forecast forecasts) {
		this.forecasts = forecasts;
	}
}
