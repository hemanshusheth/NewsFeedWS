package com.worldnewsonmap.weather.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Guid {

    @XmlAttribute(name = "isPermaLink")
    protected Boolean isPermaLink;

    /**
     * Gets the value of the isPermaLink property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPermaLink() {
        return isPermaLink;
    }

    /**
     * Sets the value of the isPermaLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPermaLink(Boolean value) {
        this.isPermaLink = value;
    }

}