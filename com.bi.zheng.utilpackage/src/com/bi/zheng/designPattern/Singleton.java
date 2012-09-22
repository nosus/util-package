package com.bi.zheng.designPattern;

/**
 * @author <a href="mailto:bizheng@gmail.com">Zheng BI</a>
 * @version $Id: $
 */
public class Singleton {
  private Singleton() {}

  synchronized public static Singleton getInstance() {
    if (m_instance == null) {
      m_instance = new Singleton();
    }
    return m_instance;
  }

  /**
   * @label Creates
   */
  private static Singleton m_instance = null;
}
