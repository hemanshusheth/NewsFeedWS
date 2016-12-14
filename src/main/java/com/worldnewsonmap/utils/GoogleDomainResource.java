/**
 * 
 */
package com.worldnewsonmap.utils;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author HEMANSHU This class reads and maintains google domain values from
 *         googleDomains.xml
 */
public class GoogleDomainResource {

	private static final String domainResourceFile = "googleDomains.xml";
	private static final Hashtable<String, String> domainTable = new Hashtable<String, String>();
	static {
		Initialize(domainResourceFile);
	}
	
	public static void Initialize(String fileName) {
		try {
			File file = new File(GoogleDomainResource.class.getClassLoader().getResource(fileName).getFile());
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			if (doc.hasChildNodes()) {
				processNodes(doc);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void processNodes(Document doc) {
		NodeList nodes = doc.getElementsByTagName("GoogleDomain");

	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element element = (Element) nodes.item(i);

	      NodeList country = element.getElementsByTagName("Country");
	      Element line = (Element) country.item(0);
	      String key = getCharacterDataFromElement(line);
	      
	      NodeList domainName = element.getElementsByTagName("GoogleDomainName");
	      line = (Element) domainName.item(0);
	      String value = getCharacterDataFromElement(line);
	      domainTable.put(key, value);
	    }
	  }

	private static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }

	/**
	 * @return the domainTable
	 */
	public static String getDomainValue(String key) {
		if(domainTable.containsKey(key))
			return domainTable.get(key);
		return "com";
	}
	
	private void destroyHashTable(Hashtable table){
		table.clear();
	}
	
	private int sizeTable(Hashtable table){
		return table.size();
	}
	
	public static Hashtable<String, String>  getDomainTable(){
		return domainTable;
	}
}
