package com.worldnewsonmap.rss.model;

import javax.xml.stream.XMLStreamException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 *  NewsItem parse through the rss description xml 
 *  to extract image source link and news story 
 */
public class NewsItem {
	private String imgSource ="";
	private String story="";

	/*
	 * NewsItem constructor accepts description HTML
	 */
	public NewsItem(String descriptionHTML) throws XMLStreamException {
		
		String html = StringEscapeUtils.unescapeHtml4(descriptionHTML);
		Document doc = Jsoup.parse(html);
		/*
		 * image link is present in img tag with src attribute length > 0
		 */
		Elements imgElements = doc.getElementsByTag("img");
		for (Element imgElement : imgElements) {
			String imgSrc = imgElement.attr("src");
			if (imgSrc.length() > 0) {
				this.imgSource = imgSrc;
				break;
			}
		}
		/*
		 * description is present in the font tag with size=-1 and length of
		 * string > 220
		 */
		Elements fontElements = doc.getElementsByTag("font");
		for (Element fontElement : fontElements) {
			String sizeValue = fontElement.attr("size");
			if (sizeValue.equals("-1")) {
				String desc = fontElement.text();
				if (desc.length() > 200) {
					this.story = desc;
					break;
				}
			}
		}
		return;
	}

	/*
	 * Gets image source url
	 */
	public String getImgSource() {
		return imgSource;
	}

	/*
	 * Gets news story
	 */
	public String getStory() {
		return story;
	}
}