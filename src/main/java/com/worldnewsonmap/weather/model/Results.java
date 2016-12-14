/**
 * 
 */
package com.worldnewsonmap.weather.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author HEMANSHU
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channel"
})
public class Results {

    @XmlElement(required = true)
    protected Channel channel;

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link Query.Results.Channel }
     *     
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Query.Results.Channel }
     *     
     */
    public void setChannel(Channel value) {
        this.channel = value;
    }
}
