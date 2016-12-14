package com.worldnewsonmap.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

import com.worldnewsonmap.utils.GoogleDomainResource;

public class GoogleDomainResourceTest {

	private final String domainResourceFile = "resources\\googleDomains.xml";
	private GoogleDomainResource gdr;
	
	@Before
	public void setUp() throws Exception {
		String fileName = domainResourceFile;
		gdr= new GoogleDomainResource();
		gdr.Initialize(fileName);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void getDomainValueTest() {
		String key1 ="Argentina";
		String value1 = gdr.getDomainValue(key1);
		assertEquals("com.ar",value1 );
		
		String key2 ="Fiji";
		String value2 = gdr.getDomainValue(key2);
		assertEquals("com.fj",value2 );
		

		String key3 ="France";
		String value3 = gdr.getDomainValue(key3);
		assertEquals("fr",value3);
	}
	
	@Test
	public void getCharacterDataFromElementTest(){
		Hashtable<String, String> h = new Hashtable<String,String>(){{put("1","1");}};
		Object[] args ={h};
		PrivateAccessor("com.worldnewsonmap.utils.GoogleDomainResource",
						 "destroyHashTable", args);
	}
	
	private Object PrivateAccessor(String targetClass, String methodName, Object[] args){
		try {
			Class<?> cls = Class.forName(targetClass);
			Object obj = cls.newInstance();
			Method method = cls.getDeclaredMethod(methodName, String.class);
			method.setAccessible(true);
			return method.invoke(cls, args);
		} catch (ClassNotFoundException | 
				 InstantiationException | 
				 IllegalAccessException | 
				 NoSuchMethodException | 
				 SecurityException | 
				 IllegalArgumentException | 
				 InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void checkDomainTest(){
		Hashtable<String, String> domainTable = gdr.getDomainTable();
		HttpURLConnection connection = null;
	    
			for (Map.Entry<String,String> entry : domainTable.entrySet()) {
				try {
		    	String hostname = "http://news.google.".concat(entry.getValue());
		        URL u = new URL(hostname);
		        connection = (HttpURLConnection) u.openConnection();
		        connection.setRequestMethod("HEAD");
		        int code = connection.getResponseCode();
				if(code != 200){
					System.out.println(entry.getKey() + " is Unreachable" );
				}
		        // You can determine on HTTP return code received. 200 is success.
		    }
		
	    catch (Exception e) {
	    	System.out.println(entry.getKey() + " is Unreachable" );
		    } finally {
		        if (connection != null) {
		            connection.disconnect();
		        }
		    }  
		}
	}
}
