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

  /*
   * ��������:�������ַ�����Ƹ�ʽ�����ع̶����� formatNumber��"-1400.235",2)=-1,400.24������λ�� ���Ա���С�����λ,�������,��0 �����,����������
   * ������С���Ķ��ŷָ�
   */
  public static String formatNumber(String number, int weishu) throws Exception {
    if (number == null || (number.trim().length() == 0)) {
      return "";
    }
    String first = number.substring(0, 1);
    if ("-".equals(first)) { // �������Ϊ���������
      number = number.substring(1);
      return first + formatPositiveNumber(number, weishu);
    } else { // �������Ϊ���������
      return formatPositiveNumber(number, weishu);
    }
  }

  public static String formatPositiveNumber(String number, int weishu) throws Exception {
    try {
      // �ж��ַ����Ƿ�Ϊ��
      if (number == null || (number.trim().length() == 0)) {
        return "";
      }
      // �ж����ַ�Ϊ"."�����
      String first = number.substring(0, 1);
      if (".".equals(first)) {
        number = "0" + number;
      }
      // �ж��ַ������г����֮��ķ����ִ���
      char[] strArray = number.toCharArray();
      for (int i = 0; i < strArray.length; i++) {
        if ((strArray[i] < '0' || strArray[i] > '9') && strArray[i] != '.') {
          return number;
        }
      }
      // �ж�С��λ��Ϊ0�����
      if (number.indexOf(".") != -1 && weishu == 0) {

        int pointNumber = number.indexOf(".");
        String strFirst = number.substring(0, pointNumber);

        // С����ǰ�ַ����ָ�
        strFirst = splitFirstNumber(strFirst);
        return strFirst;

      }

      // �ж�û��С���㣬��С��λ��Ҫ����λ�������
      if (number.indexOf(".") == -1 && weishu != 0) {

        number = number + ".";
        for (int i = 0; i < weishu; i++) {
          number = number + "0";
        }

        int pointNumber = number.indexOf(".");
        String strFirst = number.substring(0, pointNumber);
        String strLast = number.substring(pointNumber + 1, number.length());

        // С����ǰ�ַ����ָ�
        strFirst = splitFirstNumber(strFirst);

        // С������ַ����ָ�
        strLast = splitLastNumber(strLast);

        return strFirst + "." + strLast;

      }

      // �ж�û��С�����ֵ����
      if ((number.indexOf(".") == -1) && weishu == 0) {

        // С����ǰ�ַ����ָ�
        number = splitFirstNumber(number);
        return number;
      } else {
        int pointNumber = number.indexOf(".");
        String strFirst = number.substring(0, pointNumber);
        String strLast = number.substring(pointNumber + 1, number.length());

        // С��λ���ж�
        strLast = formatPoint(strLast, weishu);

        // С����ǰ�ַ����ָ�
        strFirst = splitFirstNumber(strFirst);

        // С������ַ����ָ�
        strLast = splitLastNumber(strLast);

        return strFirst + "." + strLast;
      }
    } catch (Exception e) {
      throw new Exception("���ָ�ʽ����" + e.getMessage());
    }
  }

  /**
   * @param str
   *          С����ǰ���ַ���
   * @return ����С����ǰ�Ķ��ŷָ�
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
   *          С������ַ���
   * @return ���ŷָ����ַ���
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
   *          С������ַ���
   * @param bitWei
   *          ��ȡ���ַ�������
   * @return ����С�����Ӧ�õ��ַ���
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
