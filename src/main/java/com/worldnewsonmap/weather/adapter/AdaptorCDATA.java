/**
 * 
 */
package com.worldnewsonmap.weather.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author HEMANSHU
 *
 */
public class AdaptorCDATA extends XmlAdapter<String, String> {
	@Override
    public String marshal(String s) throws Exception {
        return "<![CDATA[" + s + "]]>";
    }
    @Override
    public String unmarshal(String s) throws Exception {
	    s = s.trim();
	    if (s.startsWith("<![CDATA[")) {
	      s = s.substring(9);
	      int i = s.indexOf("]]>");
	      if (i == -1) {
	        throw new IllegalStateException(
	            "argument starts with <![CDATA[ but cannot find pairing ]]&gt;");
	      }
	      s = s.substring(0, i);
	    }
	    return s;
	}
}
