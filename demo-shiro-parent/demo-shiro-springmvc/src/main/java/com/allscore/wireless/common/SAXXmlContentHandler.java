package com.allscore.wireless.common;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXXmlContentHandler extends DefaultHandler {
	public Map<String,String> kv = new HashMap<String, String>();
	private String tagName;
	@Override  
    public void startDocument() throws SAXException {  
    }  
  
    @Override  
    public void startElement(String uri, String localName, String qName,  
            Attributes attributes) throws SAXException {  
        this.tagName = qName;  
    }  
    @Override  
    public void characters(char[] ch, int start, int length)  
            throws SAXException {  
        if (this.tagName != null) {  
            String data = new String(ch, start, length);  
            this.kv.put(this.tagName, data);
        }  
    }  
    @Override  
    public void endElement(String uri, String localName, String qName)  
            throws SAXException {  
        this.tagName = null;  
    }  
}
