package com.allscore.wireless.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class UtilXml {
	/**
	 * 组装图文消息
	 * 
	 * @param str
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @return
	 * @throws TransformerConfigurationException
	 * @throws SAXException
	 */
	public static String getOutNewsStr(List<Map<String, String>> items, String toUserName, String fromUserName,
			String createTime) throws TransformerConfigurationException, SAXException {

		StringWriter writerStr = new StringWriter();
		Result resultStr = new StreamResult(writerStr);

		SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler th = sff.newTransformerHandler();
		th.setResult(resultStr);

		Transformer transformer = th.getTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // 编码格式是UTF-8
		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // 换行
		th.startDocument(); // 开始xml文档
		AttributesImpl attr = new AttributesImpl();
        th.startElement("", "", "xml", attr); 
		UtilXml.constructTag("ToUserName", toUserName, th, attr);
		UtilXml.constructTag("FromUserName", fromUserName, th, attr);
		UtilXml.constructTag("CreateTime", createTime, th, attr);
		UtilXml.constructTag("MsgType", "news", th, attr);
		UtilXml.constructTag("FuncFlag", "0", th, attr);
		UtilXml.constructTag("ArticleCount", items.size() + "", th, attr);
        th.startElement("", "", "Articles", attr); 
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
	        th.startElement("", "", "item", attr); 
			Map<String, String> map = (Map<String, String>) iterator.next();
			if(map.get("Title")!=null){
	        th.startElement("", "", "Title", attr); 
	        th.characters(map.get("Title").toCharArray(), 0, map.get("Title").length());  
	        th.endElement("", "", "Title"); 
			}
	        th.startElement("", "", "Description", attr); 
	        th.characters(map.get("Description").toCharArray(), 0, map.get("Description").length());  
	        th.endElement("", "", "Description"); 
	        th.startElement("", "", "PicUrl", attr); 
	        th.characters(map.get("PicUrl").toCharArray(), 0, map.get("PicUrl").length());  
	        th.endElement("", "", "PicUrl"); 
	        th.startElement("", "", "Url", attr); 
	        th.characters(map.get("Url").toCharArray(), 0, map.get("Url").length());  
	        th.endElement("", "", "Url"); 
	        th.endElement("", "", "item"); 
		}
        th.endElement("", "", "Articles"); 
        th.endElement("", "", "xml"); 
		th.endDocument(); // 结束xml文档
		System.out.println("getOutNewsStr writerStr = "+writerStr);
		return writerStr.toString();
	}

	/**
	 * 组装纯文本消息
	 * 
	 * @param str
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @return
	 * @throws TransformerConfigurationException
	 * @throws SAXException
	 */
	public static String getOutTextStr(String str, String toUserName, String fromUserName, String createTime)
			throws TransformerConfigurationException, SAXException {

		StringWriter writerStr = new StringWriter();
		Result resultStr = new StreamResult(writerStr);

		SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler th = sff.newTransformerHandler();
		// th.setResult(resultXml);
		th.setResult(resultStr);

		Transformer transformer = th.getTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // 编码格式是UTF-8
		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // 换行
		th.startDocument(); // 开始xml文档
		AttributesImpl attr = new AttributesImpl();
        th.startElement("", "", "xml", attr); 
		UtilXml.constructTag("ToUserName", toUserName, th, attr);
		UtilXml.constructTag("FromUserName", fromUserName, th, attr);
		UtilXml.constructTag("CreateTime", createTime, th, attr);
		UtilXml.constructTag("MsgType", "text", th, attr);
		UtilXml.constructTag("Content", str, th, attr);
		UtilXml.constructTag("FuncFlag", "0", th, attr);
        th.endElement("", "", "xml"); 
		th.endDocument(); // 结束xml文档
		System.out.println("getOutTextStr writerStr = "+writerStr);
		return writerStr.toString();
	}

	/**
	 * 组装标签
	 * 
	 * @param tag
	 * @param value
	 * @param th
	 * @param attr
	 * @throws SAXException
	 */
	public static void constructTag(String tag, String value, TransformerHandler th, AttributesImpl attr)
			throws SAXException {
		th.startElement("", "", tag, attr);
		th.startCDATA();
		th.characters(value.toCharArray(), 0, value.length());
		th.endCDATA();
		th.endElement("", "", tag);
	}

	/**
	 * 将xml转化为map
	 * 
	 * @param xml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Map<String, String> getInputMap(String xml/* ,PrintWriter out */)
			throws ParserConfigurationException, SAXException, IOException {

		StringReader sr = new StringReader(xml);
		SAXXmlContentHandler contentHandler = new SAXXmlContentHandler();
		// 创建一个SAXParserFactory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// ②创建SAX解析器
		SAXParser parser = factory.newSAXParser();
		// ③将XML解析处理器分配给解析器
		// ④对文档进行解析，将每个事件发送给处理器。
		parser.parse(new InputSource(sr), contentHandler);
		return contentHandler.kv;
	}
	
	/** 连接到服务器并获取数据 */
	public static String getResult(String urlStr, String content) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.write(content.getBytes("utf-8"));
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}

}
