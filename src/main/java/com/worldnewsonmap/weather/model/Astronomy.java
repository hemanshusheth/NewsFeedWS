package com.worldnewsonmap.weather.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import com.worldnewsonmap.weather.adapter.TimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Astronomy {

    @XmlAttribute(name = "sunrise")
    @XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(TimeAdapter.class)
    protected Time sunrise;
    
    @XmlAttribute(name = "sunset")
    @XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(TimeAdapter.class)
    protected Time sunset;

    public Astronomy()
	{}
	
	/**
	 * @param sunrise
	 * @param sunset
	 */
	public Astronomy(Time sunrise, Time sunset) {
		this.sunrise = sunrise;
		this.sunset = sunset;
	}
    
    /**
     * Gets the value of the sunrise property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Time getSunrise() {
        return sunrise;
    }

    /**
     * Sets the value of the sunrise property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSunrise(Time value) {
        this.sunrise = value;
    }

    /**
     * Gets the value of the sunset property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Time getSunset() {
        return sunset;
    }

    /**
     * Sets the value of the sunset property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSunset(Time value) {
        this.sunset = value;
    }
    
    /**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Astronomy [sunrise=");
		builder.append(sunrise);
		builder.append(", sunset=");
		builder.append(sunset);
		builder.append("]");
		return builder.toString();
	}	
}
