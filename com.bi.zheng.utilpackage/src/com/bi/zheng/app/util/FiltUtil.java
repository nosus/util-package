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
    Properties prop = new Properties();// ���Լ��϶���   
    FileInputStream fis = new FileInputStream(fileName);
    prop.load(fis);// �������ļ���װ�ص�Properties������   
    fis.close();// �ر���   
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
    fos.close();// �ر���   
  }
  
  public void readXMLFile(String filePath) {
    File file = new File(filePath);
    readXMLFile(file);
  }
  
  public void readXMLFile(File file) {
    Element element = null;
    DocumentBuilder db = null; // documentBuilderΪ������ֱ��ʵ����(��XML�ļ�ת��ΪDOM�ļ�)
    DocumentBuilderFactory dbf = null;
    try {
      dbf = DocumentBuilderFactory.newInstance(); // ����documentBuilderFactory����
      db = dbf.newDocumentBuilder();// ����db������documentBuilderFatory�����÷���documentBuildr����
      Document dt = db.parse(file); // �õ�һ��DOM�����ظ�document����
      element = dt.getDocumentElement();// �õ�һ��elment��Ԫ��
      System.out.println("Root:" + element.getNodeName()); // ��ø��ڵ�

      NodeList childNodes = element.getChildNodes(); // ��ø�Ԫ���µ��ӽڵ�

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
