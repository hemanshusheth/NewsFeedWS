package com.worldnewsonmap.weather.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.worldnewsonmap.weather.adapter.BarometricPressureStateAdapter;
import com.worldnewsonmap.weather.adapter.FloatAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Atmosphere {

    @XmlAttribute(name = "humidity")
    protected Integer humidity;
    @XmlAttribute(name = "pressure")
    protected BigDecimal pressure;
    @XmlAttribute(name = "rising")
    @XmlJavaTypeAdapter(BarometricPressureStateAdapter.class)
    protected BarometricPressureState rising;
    @XmlAttribute(name = "visibility")
	protected Float visibility;

    public Atmosphere()
	{}
	
	/**
	 * @param humidity
	 * @param visibility
	 * @param pressure
	 * @param rising
	 */
	public Atmosphere(Integer humidity, Float visibility, BigDecimal pressure,
			BarometricPressureState rising) {
		this.humidity = humidity;
		this.visibility = visibility;
		this.pressure = pressure;
		this.rising = rising;
	}

    
    /**
     * Gets the value of the humidity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * Sets the value of the humidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHumidity(Integer value) {
        this.humidity = value;
    }

    /**
     * Gets the value of the pressure property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPressure() {
        return pressure;
    }

    /**
     * Sets the value of the pressure property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPressure(BigDecimal value) {
        this.pressure = value;
    }

    /**
     * Gets the value of the rising property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public BarometricPressureState getRising() {
        return rising;
    }

    /**
     * Sets the value of the rising property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRising(BarometricPressureState value) {
        this.rising = value;
    }

    /**
     * Gets the value of the visibility property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Float getVisibility() {
        return visibility;
    }

    /**
     * Sets the value of the visibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVisibility(Float value) {
        this.visibility = value;
    }
    
    /**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Atmosphere [humidity=");
		builder.append(humidity);
		builder.append(", visibility=");
		builder.append(visibility);
		builder.append(", pressure=");
		builder.append(pressure);
		builder.append(", rising=");
		builder.append(rising);
		builder.append("]");
		return builder.toString();
	}
}