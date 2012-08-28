package com.bi.zheng.app.util;

/**
 * @author <a href="mailto:bizheng@gmail.com">Zheng BI</a>
 * @version $Id: $
 */
/* * Db.java
 Created on 2007年8月20日, 上午 8:37
 */
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
  private String driver;
  private String url;
  private String user;
  private String password;
  private Connection conn;
  private Statement stm;
  private ResultSet rs;

  public DBUtil() {
    this("DBConf.properties");
  }

  public DBUtil(String conf) {
    loadProperties(conf);
    setConn();
  }

  public Connection getConn() {
    return this.conn;
  }

  // handle the properties file to get the informations for connection
  private void loadProperties(String conf) {
    Properties props = new Properties();
    try {
      props.load(new FileInputStream(conf));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.driver = props.getProperty("driver");
    this.url = props.getProperty("url");
    this.user = props.getProperty("user");
    this.password = props.getProperty("password");
  }

  // implement the Connection
  private void setConn() {
    try {
      Class.forName(driver);
      this.conn = DriverManager.getConnection(url, user, password);
    } catch (ClassNotFoundException classnotfoundexception) {
      classnotfoundexception.printStackTrace();
      System.err.println("db: " + classnotfoundexception.getMessage());
    } catch (SQLException sqlexception) {
      System.err.println("db.getconn(): " + sqlexception.getMessage());
    }
  }

  public void doInsert(String sql) {
    try {
      Statement statement = conn.createStatement();
      int i = stm.executeUpdate(sql);
    } catch (SQLException sqlexception) {
      System.err.println("db.executeInset:" + sqlexception.getMessage());
    }
  }

  public void doDelete(String sql) {
    try {
      stm = conn.createStatement();
      int i = stm.executeUpdate(sql);
    } catch (SQLException sqlexception) {
      System.err.println("db.executeDelete:" + sqlexception.getMessage());
    }
  }

  public void doUpdate(String sql) {
    try {
      stm = conn.createStatement();
      int i = stm.executeUpdate(sql);
    } catch (SQLException sqlexception) {
      System.err.println("db.executeUpdate:" + sqlexception.getMessage());
    }
  }

  public ResultSet doSelect(String sql) {
    try {
      stm = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
              java.sql.ResultSet.CONCUR_READ_ONLY);
      rs = stm.executeQuery(sql);
    } catch (SQLException sqlexception) {
      System.err.println("db.executeQuery: " + sqlexception.getMessage());
    }
    return rs;
  }

  public static void main(String[] args) {
    try {
      DBUtil db = new DBUtil();
      Connection conn = db.getConn();
      if (conn != null && !conn.isClosed()) {
        ResultSet rs = db.doSelect("select * from content");
        while (rs.next()) {
          System.out.println(rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3));
        }
        rs.close();
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

//
// DBConf.properties:
// driver=oracle.jdbc.driver.OracleDriver
// url=jdbc:oracle:thin:@tdt151:1521:train
// user=XX
// password=XX

