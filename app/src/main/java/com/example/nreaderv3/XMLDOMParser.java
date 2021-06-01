package com.example.nreaderv3;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLDOMParser {
    // phương thức này nhận vào một chuỗi HTML/XML, định dạng chuỗi và trả về một Document (đại diện HTML/XML)
    public Document getDocument(String xml)
    {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder db = factory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            is.setEncoding("UTF-8");
            document = db.parse(is);
        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        } catch(IOException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        }
        return document;
    }

    // nhận vào một phần tử của nodeList và thuộc tính, trả về giá trị của thuộc tính đó
    public String getValue(Element item, String name) {
        NodeList nodes = item.getElementsByTagName(name);
        return this.getTextNodeValue(nodes.item(0));
    }

    private final String getTextNodeValue(Node elem) {
        // lọc ra node đầu tiên thỏa mãn và trả về giá trị của nó
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                // cho node child bằng giá trị của phần tử đầu tiên của elem, nếu nó là rỗng thì chuyển sang giá trị của node kể tiếp
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}

