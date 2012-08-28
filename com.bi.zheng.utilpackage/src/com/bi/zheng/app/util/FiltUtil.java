package com.bi.zheng.app.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author <a href="mailto:bizheng@gmail.com">Zheng BI</a>
 * @version $Id: $
 */
public class FiltUtil {

  /**
   * @param args
   */
  public static void main(String[] args) {
  }
  
  public Properties readPropertiesFile(String fileName) throws IOException {
    Properties prop = new Properties();// 属性集合对象   
    FileInputStream fis = new FileInputStream(fileName);
    prop.load(fis);// 将属性文件流装载到Properties对象中   
    fis.close();// 关闭流   
    return prop;
  }
  
  public String getValueFromPropertiesFile(String fileName, String propertyName) throws IOException {
    return readPropertiesFile(fileName).getProperty(propertyName);
  }
  
  public void setValueToPropertiesFile(String fileName, String propertyName, String value) throws IOException {
    Properties prop = readPropertiesFile(fileName);
    prop.setProperty(propertyName, value); 
    FileOutputStream fos = new FileOutputStream(fileName);   
    prop.store(fos, "Copyright (c) bizheng");   
    fos.close();// 关闭流   
  }
  
  public void readXMLFile(String filePath) {
    File file = new File(filePath);
    readXMLFile(file);
  }
  
  public void readXMLFile(File file) {
    Element element = null;
    DocumentBuilder db = null; // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
    DocumentBuilderFactory dbf = null;
    try {
      dbf = DocumentBuilderFactory.newInstance(); // 返回documentBuilderFactory对象
      db = dbf.newDocumentBuilder();// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
      Document dt = db.parse(file); // 得到一个DOM并返回给document对象
      element = dt.getDocumentElement();// 得到一个elment根元素
      System.out.println("Root:" + element.getNodeName()); // 获得根节点

      NodeList childNodes = element.getChildNodes(); // 获得根元素下的子节点

      for (int i = 0; i < childNodes.getLength(); i++) {
        Node node = childNodes.item(i);
        System.out.println(node.getNodeName() + node.getNodeType() + node.getNodeValue());
        System.out.println(node.getTextContent());
        NodeList nodeDetail = node.getChildNodes(); 
        for (int j = 0; j < nodeDetail.getLength(); j++) {
           //do whatever you want
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
