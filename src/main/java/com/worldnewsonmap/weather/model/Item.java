package com.worldnewsonmap.weather.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.worldnewsonmap.utils.Constants;
import com.worldnewsonmap.weather.adapter.AdaptorCDATA;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "title",
    "lat",
    "_long",
    "link",
    "pubDate",
    "condition",
    "description",
    "forecast",
    "guid"
})
public class Item {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true, name="lat", namespace=Constants.GEO_NAMESPACE_URI)
    protected BigDecimal lat;
    @XmlElement(required = true, name = "long", namespace=Constants.GEO_NAMESPACE_URI)
    protected BigDecimal _long;
    @XmlElement(required = true)
    protected String link;
    @XmlElement(required = true)
    protected String pubDate;
    @XmlElement(required = true, namespace=Constants.YWEATHER_NAMESPACE_URI)
    protected Condition condition;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    protected String description;
    @XmlElement(required = true, namespace=Constants.YWEATHER_NAMESPACE_URI, name="forecast")
    protected Forecast forecast;
    @XmlElement(required = true)
    protected Guid guid;

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
     * Gets the value of the lat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * Sets the value of the lat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLat(BigDecimal value) {
        this.lat = value;
    }

    /**
     * Gets the value of the long property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLong() {
        return _long;
    }

    /**
     * Sets the value of the long property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLong(BigDecimal value) {
        this._long = value;
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
     * Gets the value of the pubDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Sets the value of the pubDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPubDate(String value) {
        this.pubDate = value;
    }

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Item.Condition }
     *     
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Item.Condition }
     *     
     */
    public void setCondition(Condition value) {
        this.condition = value;
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
     * Gets the value of the forecast property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Item.Forecast }
     *     
     */
    public Forecast getForecast() {
        return forecast;
    }

    /**
     * Sets the value of the forecast property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Item.Forecast }
     *     
     */
    public void setForecast(Forecast value) {
        this.forecast = value;
    }

    /**
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel.Item.Guid }
     *     
     */
    public Guid getGuid() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel.Item.Guid }
     *     
     */
    public void setGuid(Guid value) {
        this.guid = value;
    }
    
    /**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [title=");
		builder.append(title);
		builder.append(", link=");
		builder.append(link);
		builder.append(", description=");
		builder.append(description);
		builder.append(", guid=");
		builder.append(guid);
		builder.append(", pubDate=");
		builder.append(pubDate);
		builder.append(", geoLat=");
		builder.append(lat);
		builder.append(", geoLong=");
		builder.append(_long);
		builder.append(", condition=");
		builder.append(condition);
		builder.append(", forecasts=");
		builder.append(forecast);
		builder.append("]");
		return builder.toString();
	}
}
