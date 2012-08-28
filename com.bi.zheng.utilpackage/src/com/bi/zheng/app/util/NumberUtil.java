package com.bi.zheng.app.util;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:bizheng@gmail.com">Zheng BI</a>
 * @version $Id: $
 */
public class NumberUtil {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  /**
   * �ж�����һ�������Ƿ�����
   * 
   * @param n
   * @return boolean
   */
  public static boolean isPrimes(int n) {
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * �������һ�������Ľ׳ˣ��ݹ�
   * 
   * @param n
   * @return n!
   */
  public static int factorial(int n) {
    if (n == 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }

  /**
   * ��ָ��byte������16���Ƶ���ʽ��ӡ������̨
   * 
   * @param hint
   *          String
   * @param b
   *          byte[]
   * @return void
   */
  public static void printHexString(String hint, byte[] b) {
    System.out.print(hint);
    for (int i = 0; i < b.length; i++) {
      String hex = Integer.toHexString(b[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      System.out.print(hex.toUpperCase() + " ");
    }
    System.out.println("");
  }

  public static double decimalFormatD(int num, double d) {
    String format = "0.";
    String result = "";
    double db;

    for (int i = 0; i < num; i++)
      format = format.concat("0");

    DecimalFormat decimal = new DecimalFormat(format);
    result = decimal.format(d);
    db = Double.parseDouble(result);

    return db;
  }

  public static float decimalFormatF(int num, float f) {
    String format = "0.";
    String result = "";
    float fl;

    for (int i = 0; i < num; i++)
      format = format.concat("0");

    DecimalFormat decimal = new DecimalFormat(format);
    result = decimal.format(f);
    fl = Float.parseFloat(result);

    return fl;
  }

  public static String doubleToString(double f) {
    String s = "";
    double a = 0;

    while (f >= 1) {

      a = f % ((double) 10);

      s = String.valueOf((int) a) + s;
      f = (f - a) / 10;
    }
    return s;
  }

  /**
   * һ��forѭ����ӡ�žų˷���
   */
  public void nineNineMultiTable() {
    for (int i = 1, j = 1; j <= 9; i++) {
      System.out.print(i + "*" + j + "=" + i * j + " ");
      if (i == j) {
        i = 0;
        j++;
        System.out.println();
      }
    }
  }

  /**
   * �ж��ǲ��ǺϷ��ֻ� handset �ֻ�����
   */
  public static boolean isHandset(String handset) {
    try {
      if (!handset.substring(0, 1).equals("1")) {
        return false;
      }
      if (handset == null || handset.length() != 11) {
        return false;
      }
      String check = "^[0123456789]+$";
      Pattern regex = Pattern.compile(check);
      Matcher matcher = regex.matcher(handset);
      boolean isMatched = matcher.matches();
      if (isMatched) {
        return true;
      } else {
        return false;
      }
    } catch (RuntimeException e) {
      return false;
    }
  }
}
