/**
 * 
 */
package com.worldnewsonmap.weather.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.worldnewsonmap.weather.model.BarometricPressureState;

/**
 * @author HEMANSHU
 *
 */
public class BarometricPressureStateAdapter extends XmlAdapter<Integer, BarometricPressureState>{
	private static final int FALLING = 2;
	private static final int RISING = 1;
	private static final int STEADY = 0;
	@Override
	public BarometricPressureState unmarshal(Integer v) throws Exception {
		switch (v) {
			case STEADY: return BarometricPressureState.STEADY;
			case RISING: return BarometricPressureState.RISING;
			case FALLING: return BarometricPressureState.FALLING;
		}
		return null;
	}

	@Override
	public Integer marshal(BarometricPressureState v) throws Exception {
		switch (v) {
			case STEADY: return STEADY;
			case RISING: return RISING;
			case FALLING: return FALLING;
		}
		return null;
	}
}
