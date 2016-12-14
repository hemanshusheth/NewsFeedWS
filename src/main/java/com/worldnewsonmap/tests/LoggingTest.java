package com.worldnewsonmap.tests;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoggingTest {
      private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	  private static Logger logger = Logger.getLogger("LoggingTest");
	  private static FileWriter fw;
	  private static StringBuilder sb = new StringBuilder();
	  
    @BeforeClass 
    public static void setupJerseyLog() throws Exception {
    	fw =new FileWriter("myLog.txt");
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
            	  try {
					fw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
              }

              public void close() {
            	  try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
              }
            });
        Logger.getLogger("com.sun.jersey").setLevel(Level.FINEST);
    	
    }
	// Your test methods here ...
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
	    logger.warning("This is Logging Warning");
	    logger.info("This is Logging Info");
	    logger.severe("This is Logging severe");
	    System.out.print("Test Result"+ sb.toString());
	    fw.append(sb.toString());
	}
}
