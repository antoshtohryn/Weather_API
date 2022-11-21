package com.example.lab_5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Parser {
    public static String getRateFromECB(InputStream stream) throws IOException {
        String result = "";
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            NodeList cityNodes = doc.getElementsByTagName(Constants.API_NAME);
            Element name = (Element) cityNodes.item(0);

            NodeList tempNodes = doc.getElementsByTagName(Constants.API_TEMP);
            Element temp = (Element) tempNodes.item(0);

            result = name.getTextContent() + " " + temp.getTextContent();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return result;
    }
}
