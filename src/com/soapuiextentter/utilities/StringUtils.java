package com.soapuiextentter.utilities;

import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

public class StringUtils {

	public static String prettyPrintXML(String xmlString) {
		String preetyXML = xmlString;
		try {
			InputSource src = new InputSource(new String(xmlString));
			org.w3c.dom.Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
					.getDocumentElement();
			Boolean keepDeclaration = Boolean.valueOf(xmlString.startsWith("<?xml"));
			DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			LSSerializer writer = impl.createLSSerializer();
			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);
			preetyXML = writer.writeToString(document);
		} catch (Exception e) {
			return preetyXML;
		}
		return preetyXML;
	}

	public static String prettyPrintJson(String jsonString) {
		String preetyJson = jsonString;
		try {
			preetyJson = (new JSONObject(jsonString)).toString(4);
		} catch (Exception e) {
			return preetyJson;
		}
		return preetyJson;
	}
}
