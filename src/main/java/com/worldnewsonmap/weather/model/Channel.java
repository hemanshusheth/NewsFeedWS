package com.worldnewsonmap.weather.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.worldnewsonmap.utils.Constants;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/*@XmlType(name = "", propOrder = {
    "title",
    "link",
    "description",
    "language",
    "lastBuildDate",
    "ttl",
    "location",
    "units",
    "wind",
    "atmosphere",
    "astronomy",
    "image",
    "item"
})*/
public class Channel {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String link;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String language;
    @XmlElement(required = true)
    protected String lastBuildDate;
    protected int ttl;
    
	@XmlElement(namespace=Constants.YWEATHER_NAMESPACE_URI)
    protected Location location;
    
    @XmlElement(namespace=Constants.YWEATHER_NAMESPACE_URI)
    protected Units units;
    
    @XmlElement(namespace=Constants.YWEATHER_NAMESPACE_URI)
    protected Wind wind;

	@XmlElement(namespace=Constants.YWEATHER_NAMESPACE_URI)
    protected Atmosphere atmosphere;

	@XmlElement(namespace=Constants.YWEATHER_NAMESPACE_URI)
    protected Astronomy astronomy;
	
    @XmlElement(required = true)
    protected Image image;
    @XmlElement(required = true)
    protected Item item;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the lastBuildDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastBuildDate() {
        return lastBuildDate;
    }

    /**
     * Sets the value of the lastBuildDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastBuildDate(String value) {
        this.lastBuildDate = value;
    }

    /**
     * Gets the value of the ttl property.
     * 
     */
    public int getTtl() {
        return ttl;
    }

    /**
     * Sets the value of the ttl property.
     * 
     */
    public void setTtl(int value) {
        this.ttl = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Units }
     *     
     */
    public Units getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Units }
     *     
     */
    public void setUnits(Units value) {
        this.units = value;
    }

    /**
     * Gets the value of the wind property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Wind }
     *     
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Sets the value of the wind property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Wind }
     *     
     */
    public void setWind(Wind value) {
        this.wind = value;
    }

    /**
     * Gets the value of the atmosphere property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Atmosphere }
     *     
     */
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    /**
     * Sets the value of the atmosphere property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Atmosphere }
     *     
     */
    public void setAtmosphere(Atmosphere value) {
        this.atmosphere = value;
    }

    /**
     * Gets the value of the astronomy property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Astronomy }
     *     
     */
    public Astronomy getAstronomy() {
        return astronomy;
    }

    /**
     * Sets the value of the astronomy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Astronomy }
     *     
     */
    public void setAstronomy(Astronomy value) {
        this.astronomy = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Image }
     *     
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Image }
     *     
     */
    public void setImage(Image value) {
        this.image = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Item }
     *     
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Item }
     *     
     */
    public void setItem(Item value) {
        this.item = value;
    }
    
    /**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Channel [title=");
		builder.append(title);
		builder.append(", link=");
		builder.append(link);
		builder.append(", language=");
		builder.append(language);
		builder.append(", description=");
		builder.append(description);
		builder.append(", lastBuildDate=");
		builder.append(lastBuildDate);
		builder.append(", ttl=");
		builder.append(ttl);
		builder.append(", location=");
		builder.append(location);
		builder.append(", units=");
		builder.append(units);
		builder.append(", wind=");
		builder.append(wind);
		builder.append(", atmosphere=");
		builder.append(atmosphere);
		builder.append(", astronomy=");
		builder.append(astronomy);
		builder.append(", image=");
		builder.append(image);
		builder.append(", item=");
		builder.append(item);
		builder.append("]");
		return builder.toString();
	}
}