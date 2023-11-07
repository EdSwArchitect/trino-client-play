package com.teamraft.services;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Instant;

public class XmlHelper {
    private final DocumentBuilder documentBuilder;
    private final XPath xPath;
    public XmlHelper() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // Set the following attributes to mitigate XXE attacks (OWASP-A4 vulns, MITRE CWE-611/827)
        try {
            documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        } catch ( IllegalArgumentException iae ) {
            // not all XML impls have this. With XOM some don't seem to support.
        }
        try {
            this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        this.xPath = XPathFactory.newDefaultInstance().newXPath();
    }
    public String getField(String xPath, Document document) throws XPathExpressionException {
        return this.xPath.evaluate(xPath, document);
    }

    public Instant getInstant(String xPath, Document document) throws XPathExpressionException {
        String field = getField(xPath, document);
        if (field == null || field.isEmpty()) {
            return null;
        }
        return Instant.parse(field);
    }


    public String getValues(String xPath, Document document) throws XPathExpressionException {
        NodeList result = (NodeList) this.xPath.evaluate(xPath, document, XPathConstants.NODESET);
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < result.getLength(); index++) {
            Node node = result.item(index);
            String value = node.getNodeValue();
            if (index > 0) {
                sb.append(" ");
            }
            sb.append(value);
        }
        return sb.toString();
    }

    public Document convertToDocument(String message) throws IOException, SAXException {
        return documentBuilder.parse(new ByteArrayInputStream(message.getBytes()));
    }

}
