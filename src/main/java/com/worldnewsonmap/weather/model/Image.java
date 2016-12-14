package com.worldnewsonmap.weather.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "title",
    "width",
    "height",
    "link",
    "url"
})
public class Image {

	/**
	 * The title of the image, for example "Yahoo! Weather".
	 */
    @XmlElement(required = true)
    protected String title;
    /**
	 * The width of the image, in pixels.
	 */
	@XmlElement
    protected int width;
	/**
	 * The height of the image, in pixels.
	 */
	@XmlElement
    protected int height;
	/**
	 * The URL of Yahoo! Weather.
	 */
	@XmlElement(required = true)
    protected String link;
    @XmlElement(required = true)
    /**
	 * The URL of the image.
	 */
    protected String url;

    public Image()
	{}
	
	/**
	 * @param title
	 * @param link
	 * @param url
	 * @param width
	 * @param height
	 */
	public Image(String title, String link, String url, int width,
			int height) {
		this.title = title;
		this.link = link;
		this.url = url;
		this.width = width;
		this.height = height;
	}
    
    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the width property.
     * 
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     */
    public void setWidth(int value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     */
    public void setHeight(int value) {
        this.height = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }
    
    /**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Image [title=");
		builder.append(title);
		builder.append(", link=");
		builder.append(link);
		builder.append(", url=");
		builder.append(url);
		builder.append(", width=");
		builder.append(width);
		builder.append(", height=");
		builder.append(height);
		builder.append("]");
		return builder.toString();
	}

}
