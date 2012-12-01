package us.newplatyp.mtgcardmaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import us.newplatyp.util.Configuration;

public class CardsXMLReader {
	private List<Card> cards;
	private Document dom;
	private Configuration config;

	public CardsXMLReader(Configuration config) {
		this.config = config;
		//create a list to hold the employee objects
		cards = new ArrayList<Card>();
	}

	public void goForIt(String uri) {
		
		//parse the xml file and get the dom object
		parseXmlFile(uri);
		
		//get each employee element and create a Employee object
		parseDocument();
		
		//Iterate through the list and print the data
		//printData();
	}
	
	public List<Card> getCards() {
		return this.cards;
	}

	private void parseXmlFile(String uri){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(uri);
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	
	private void parseDocument(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		
		//get a nodelist of <card> elements
		NodeList nl = docEle.getElementsByTagName("card");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0 ; i < nl.getLength(); i++) {
				
				//get the employee element
				Element el = (Element)nl.item(i);
				
				//get the Card object
				Card c = this.getCard(el);
				
				//add it to list
				this.cards.add(c);
			}
		}
	}


	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 * @param empEl
	 * @return
	 */
	private Card getCard(Element empEl) {
		
		//for each <card> element get text or int values of 
		//name ,id, age and name
		String title = getTextValue(empEl,"title");
		String text = getTextValue(empEl,"text");
		String flavortext = getTextValue(empEl,"flavortext");
		String rawMana = getTextValue(empEl,"manacost");
		String imageURI = getTextValue(empEl,"imageuri");
		String artist = getTextValue(empEl,"artist");
		int power = getIntValue(empEl,"power");
		int toughness = getIntValue(empEl,"toughness");
		int loyalty = getIntValue(empEl,"loyalty");

		//String type = empEl.getAttribute("type");
		
		//Create a new Employee with the value read from the xml nodes
		Card c = new Card(title);
		c.setText(text);
		c.setFlavorText(flavortext);
		c.setImageURI(imageURI);
		c.setArtist(artist);
		c.setPower(power);
		c.setToughness(toughness);
		c.setLoyalty(loyalty);
		c.setManaFromText(rawMana);

		//get a nodelist of <types> elements
		NodeList nl = empEl.getElementsByTagName("types");
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			boolean go = true;
			int count = 0;
			while (go && count < 10) {
				String val = this.getTextValue(el, "type", count);
				count++;
				go = val != null;
				if (go) {
					c.addType(val);
				}
			}
		}

		//get a nodelist of <subtypes> elements
		nl = empEl.getElementsByTagName("subtypes");
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			boolean go = true;
			int count = 0;
			while (go && count < 10) {
				String val = this.getTextValue(el, "subtype", count);
				count++;
				go = val != null;
				if (go) {
					c.addSubtype(val);
				}
			}
		}

		return c;
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content 
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is name I will return John  
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private String getTextValue(Element ele, String tagName) {
		return this.getTextValue(ele, tagName, 0);
	}
	private String getTextValue(Element ele, String tagName, int pos) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > pos) {
			Element el = (Element)nl.item(pos);
			if (el != null & el.getFirstChild() != null) {
				textVal = el.getFirstChild().getNodeValue();
			}
		}

		return textVal;
	}

	
	/**
	 * Calls getTextValue and returns a int value
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		try {
			return Integer.parseInt(getTextValue(ele,tagName));
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Iterate through the list and print the 
	 * content to console
	 */
	private void printData(){
		
		System.out.println("No of Cards '" + cards.size() + "'.");
		
		Iterator it = cards.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

}
