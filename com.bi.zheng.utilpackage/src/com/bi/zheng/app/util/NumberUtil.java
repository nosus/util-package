package com.bi.zheng.app.util;

import java.math.BigDecimal;
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
   * 判断任意一个整数是否素数
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
   * 获得任意一个整数的阶乘，递归
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
   * 将指定byte数组以16进制的形式打印到控制台
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
   * 一个for循环打印九九乘法表
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
   * 判断是不是合法手机 handset 手机号码
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

  /*
   * 功能描述:将数字字符按会计格式整理返回固定长度 formatNumber（"-1400.235",2)=-1,400.24保留两位） 可以保留小数点后几位,如果不足,补0 如果足,就四舍五入
   * 整数和小数的逗号分割
   */
  public static String formatNumber(String number, int weishu) throws Exception {
    if (number == null || (number.trim().length() == 0)) {
      return "";
    }
    String first = number.substring(0, 1);
    if ("-".equals(first)) { // 针对数字为负数的情况
      number = number.substring(1);
      return first + formatPositiveNumber(number, weishu);
    } else { // 针对数字为正数的情况
      return formatPositiveNumber(number, weishu);
    }
  }

  public static String formatPositiveNumber(String number, int weishu) throws Exception {
    try {
      // 判断字符串是否为空
      if (number == null || (number.trim().length() == 0)) {
        return "";
      }
      // 判断首字符为"."的情况
      String first = number.substring(0, 1);
      if (".".equals(first)) {
        number = "0" + number;
      }
      // 判断字符串里有除点号之外的非数字存在
      char[] strArray = number.toCharArray();
      for (int i = 0; i < strArray.length; i++) {
        if ((strArray[i] < '0' || strArray[i] > '9') && strArray[i] != '.') {
          return number;
        }
      }
      // 判断小数位数为0的情况
      if (number.indexOf(".") != -1 && weishu == 0) {

        int pointNumber = number.indexOf(".");
        String strFirst = number.substring(0, pointNumber);

        // 小数点前字符串分割
        strFirst = splitFirstNumber(strFirst);
        return strFirst;

      }

      // 判断没有小数点，但小数位数要求有位数的情况
      if (number.indexOf(".") == -1 && weishu != 0) {

        number = number + ".";
        for (int i = 0; i < weishu; i++) {
          number = number + "0";
        }

        int pointNumber = number.indexOf(".");
        String strFirst = number.substring(0, pointNumber);
        String strLast = number.substring(pointNumber + 1, number.length());

        // 小数点前字符串分割
        strFirst = splitFirstNumber(strFirst);

        // 小数点后字符串分割
        strLast = splitLastNumber(strLast);

        return strFirst + "." + strLast;

      }

      // 判断没有小数部分的情况
      if ((number.indexOf(".") == -1) && weishu == 0) {

        // 小数点前字符串分割
        number = splitFirstNumber(number);
        return number;
      } else {
        int pointNumber = number.indexOf(".");
        String strFirst = number.substring(0, pointNumber);
        String strLast = number.substring(pointNumber + 1, number.length());

        // 小数位数判断
        strLast = formatPoint(strLast, weishu);

        // 小数点前字符串分割
        strFirst = splitFirstNumber(strFirst);

        // 小数点后字符串分割
        strLast = splitLastNumber(strLast);

        return strFirst + "." + strLast;
      }
    } catch (Exception e) {
      throw new Exception("数字格式出错" + e.getMessage());
    }
  }

  /**
   * @param str
   *          小数点前的字符串
   * @return 返回小数点前的逗号分割
   */
  public static String splitFirstNumber(String str) {
    StringBuffer strBuffer = new StringBuffer();
    strBuffer.append(str);
    strBuffer.reverse();
    str = strBuffer.toString();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < str.length(); i = i + 3) {
      if (i + 3 < str.length()) {
        String str1 = str.substring(i, i + 3);
        sb.append(str1);
        sb.append(",");
      } else {
        String str2 = str.substring(i, str.length());
        sb.append(str2);
      }
    }
    sb.reverse();
    return sb.toString();
  }

  /**
   * @param str
   *          小数点后字符串
   * @return 逗号分割后的字符串
   */
  public static String splitLastNumber(String str) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < str.length(); i = i + 3) {
      if (i + 3 < str.length()) {
        String str1 = str.substring(i, i + 3);
        sb.append(str1);
        sb.append(",");
      } else {
        String str2 = str.substring(i, str.length());
        sb.append(str2);
      }
    }
    return sb.toString();
  }

  /**
   * @param laststr
   *          小数点后字符串
   * @param bitWei
   *          截取的字符串长度
   * @return 返回小数点后应得的字符串
   */
  public static String formatPoint(String laststr, int bitWei) {
    int n = laststr.length();
    if (n < bitWei) {
      for (int i = 0; i < bitWei - n; i++) {
        laststr = laststr + "0";
      }
    } else {
      BigDecimal bd = new BigDecimal("0." + laststr).setScale(bitWei, BigDecimal.ROUND_HALF_UP);
      laststr = bd.toString();
      laststr = laststr.substring(2, laststr.length());
    }
    return laststr;
  }
}
