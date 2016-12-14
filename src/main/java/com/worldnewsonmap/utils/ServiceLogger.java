/**
 * 
 */
package com.worldnewsonmap.utils;

import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @author HEMANSHU
 *
 */
public class ServiceLogger {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static Logger logger = Logger.getLogger("RESTfulNewsService");
	private static StringBuilder sb = new StringBuilder();
	  
	static 
	{
		Logger.getLogger("").addHandler(new Handler() {
			public void publish(LogRecord logRecord) {
            	sb.append(new Date(logRecord.getMillis()))
                .append(" ")
                .append(logRecord.getLevel().getLocalizedName())
                .append(": ")
                .append("*" + logRecord.getMessage() + "*")
            	.append(LINE_SEPARATOR);
              }

              public void flush() {
            	  
              }

              public void close() {
              }
            });
		Logger.getLogger("com.sun.jersey").setLevel(Level.FINEST);
	}
   	
	
	public static void Info(String message){
		logger.info(message);
	}
	
	public static void Warning(String message){
		logger.warning(message);
	}
	
	public static void Severe(String message){
		logger.severe(message);
	}

}
