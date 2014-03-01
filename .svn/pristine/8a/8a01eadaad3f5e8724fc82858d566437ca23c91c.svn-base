package com.seatbookie.svg.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

public class XMLer {

	private Document document = null;
	private Element element = null;

	public XMLer(Element element) {
		super();
		this.element = element;
	}

	public String getNodeName() {
		return this.element.getNodeName();
	}

	public XMLer(InputStream is) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		document = dBuilder.parse(is);
		element = document.getDocumentElement();
	}

	public XMLer(String urlOrBody) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		document = dBuilder.parse(urlOrBody);
		element = document.getDocumentElement();
	}

	public ArrayList<Element> findElements(String tagRegex) {
		return findElements(element, tagRegex);
	}

	public static ArrayList<Element> findElements(Element e, String tagRegex) {
		ArrayList<Element> ans = new ArrayList<Element>();
		NodeList nList = e.getChildNodes();
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element ei = (Element) nNode;
				if (nNode.getNodeName().matches(tagRegex)) {
					ans.add(ei);
				}
				ans.addAll(findElements(ei, tagRegex));
			}
		}
		return ans;
	}

	public ArrayList<XMLer> findXMLers(String tagRegex) {
		return findXMLers(this, tagRegex);
	}

	public static ArrayList<XMLer> findXMLers(XMLer er, String tagRegex) {
		ArrayList<Element> ale = findElements(er.element, tagRegex);
		ArrayList<XMLer> ans = new ArrayList<XMLer>();
		for (Element e : ale) {
			ans.add(new XMLer(e));
		}
		return ans;
	}

	@Override
	public String toString() {
		return convertString(this.element);
	}

	public static String convertString(Element node) {
		Document document = node.getOwnerDocument();
		DOMImplementationLS domImplLS = (DOMImplementationLS) document
				.getImplementation();
		LSSerializer serializer = domImplLS.createLSSerializer();
		String str = serializer.writeToString(node);
		return str;
	}

	public String getTagValue() {
		StringBuilder sb = new StringBuilder();
		NodeList nlList = element.getChildNodes();
		for (int i = 0; i < nlList.getLength(); i++) {
			Node nValue = (Node) nlList.item(i);
			sb.append(nValue.getNodeValue());
		}
		return sb.toString();
	}

	public String getTagAttributeValue(String attribute) {
		return element.getAttribute(attribute);
	}

	public String getTagValue(String key) {
		return findXMLers(key).get(0).getTagValue();
	}

	public Integer getTagInt(String key) {
		return Integer.parseInt(getTagValue(key));
	}

	public Double getTagDouble(String key) {
		return Double.parseDouble(getTagValue(key));
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		XMLer xmlProvider = new XMLer(
				"http://ppg.naif.org.tw//naif/service/infopower.asmx/Market_Year_Pig_Quotations?QueryDate=2012-11-01");
		ArrayList<XMLer> xmls = xmlProvider
				.findXMLers("Market_Year_Pig_Quotations");
		System.out.println(xmls);

	}

}
