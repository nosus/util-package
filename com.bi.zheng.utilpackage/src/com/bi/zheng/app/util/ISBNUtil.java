package com.bi.zheng.app.util;

/**
 * @author <a href="mailto:bizheng@gmail.com">Zheng BI</a>
 * @version $Id: $
 */
public final class ISBNUtil {

  /**
   * ���������ISBN�ţ�����ISBN����Ч�ԡ����� GB/T 5795-2006 �� ISO 2108:2005 ISBN
   * 10λ��׼��13λ��׼ʵ�֣�13λ��׼��2007��1��1�տ�ʼʵ�У��ڴ�֮ǰ����10λ��׼����
   * 
   * @param String
   *          isbn����Ҫ����У���ISBN�ַ���
   * @return true���������ISBNУ����ȷ��<br/>
   *         false���������ISBNУ�����
   */
  public static boolean checkISBN(String isbn) {

    int count = 0;
    int checkBitInt = 0;

    // ��ISBN����ȫȡ��д��ĸ
    // isbn = isbn.toUpperCase();

    char[] cs = isbn.toCharArray();
    switch (isbn.length()) {
    case 10:
      // ****************************************************************
      // ��ISBNΪ10λʱ�����е�У�飬����2007��1��1��ǰ�ĳ�����
      // ���ݸ�ʽ����������ǰ9λΪISBN���ݣ���10λΪУ��λ
      // У�鷽����
      // (1) �������ҽ�ǰ9λ���ݴ�10��ʼ��2���б�ţ���ΪλȨ
      // (2) ��9λ�������λλȨ���м�Ȩ��������9λ�ͣ���Ϊ��Ȩ�ͣ�����M��
      // (3) ��10λУ��λ���㷽����У��λΪC��
      // M + C �� 0 (mod 11)
      // CΪ10ʱ��������X��
      // ****************************************************************

      // ȡ��ǰ9λ���ֽ��м�Ȩ�ͼ���
      for (int i = 0; i < 9; i++) {
        // ��ǰ9λ�������з������ַ������׳��쳣
        if (cs[i] < '0' || cs[i] > '9') {
          throw new ISBNFormatException("ISBN " + isbn + " �� " + (i + 1) + " λ�г��ַǷ��ַ� " + cs[i]);
        }

        int c = cs[i] - '0';
        // ���Ȩ��
        count += c * (10 - i);
      }

      // ȡ��У��λ����0��9��X����У���ַ�Ҫ��
      if (cs[9] >= '0' && cs[9] <= '9') {
        checkBitInt = cs[9] - '0';
      } else if (cs[9] == 'X' || cs[9] == 'x') {
        // У��λ�еġ�X����ʾ���ݡ�10��
        checkBitInt = 10;
      } else {
        // ��0��9��Xʱ�׳��쳣
        throw new ISBNFormatException("ISBN " + isbn + " �� 10 λ�г��ַǷ��ַ� " + cs[9]);
      }

      // ����У��
      if ((count + checkBitInt) % 11 == 0) {
        return true; // У��ɹ�
      } else {
        return false; // У��ʧ��
      }
    case 13:
      // ****************************************************************
      // ��ISBNΪ13λʱ�����е�У�飬����2007��1��1�պ�ĳ�����
      // ���ݸ�ʽ����������ǰ12λΪISBN���ݣ���13λΪУ��λ
      // У�鷽����
      // (1) �������ҽ�ǰ12λ����ȡ����λ���ͺ�żλ����
      // (2) ��żλ���ͳ�3����������λ���͵ĺͣ��ü�Ȩ��
      // (3) ��13λУ��λ���㷽����У��λΪC��
      // M + C �� 0 (mod 10)
      // ****************************************************************

      // ISBNΪ13λ����ʱ��ǰ3λĿǰֻ���ǡ�978������ʵ�У���979������δʵ�У�
      if (!isbn.startsWith("978") && !isbn.startsWith("979")) {
        throw new ISBNFormatException("ISBN-13 ��ʽ�����ϱ�׼");
      }
      // ȡ��ǰ12λ���ֽ��м�Ȩ�ͼ���
      int countEven = 0;
      int countOdd = 0;
      for (int i = 0; i < 12; i++) {
        int c = cs[i] - '0';
        // ��ǰ12λ�������з������ַ������׳��쳣
        if (c < 0 || c > 9) {
          throw new ISBNFormatException("ISBN " + isbn + " �� " + (i + 1) + " λ�г��ַǷ��ַ� " + cs[i]);
        }
        // �ֱ������λ����żλ���ĺ�
        if ((i & 0x1) == 0) {
          countOdd += c;
        } else {
          countEven += c;
        }
      }
      // ���Ȩ��
      count = countOdd + (countEven * 3);

      // ȡ��У��λ����
      if (cs[12] < '0' || cs[12] > '9') {
        // У��λΪ��0~9�ַ�ʱ���׳��쳣
        throw new ISBNFormatException("ISBN " + isbn + " �� 13 λ�г��ַǷ��ַ� " + cs[12]);
      }

      checkBitInt = cs[12] - '0';
      // ����У��
      if ((count + checkBitInt) % 10 == 0) {
        return true; // У��ɹ�
      } else {
        return false; // У��ʧ��
      }
    default:
      // ISBNΪ��10λ��13λʱ�׳��쳣
      throw new ISBNFormatException("ISBN ��ʽ�����ϱ�׼");
    }
  }

  public static void main(String[] args) {
    System.out.println("9787302155638 " + ISBNUtil.checkISBN("9787302155638"));
    System.out.println("7564105607 " + ISBNUtil.checkISBN("7564105607"));
    System.out.println("730213880X " + ISBNUtil.checkISBN("730213880X"));
    System.out.println("7302138800 " + ISBNUtil.checkISBN("7302138800"));
    System.out.println("9790000000000 " + ISBNUtil.checkISBN("9790000000000"));
    try {
      System.out.println(ISBNUtil.checkISBN("9770000000000"));
    } catch (Exception e) {
      System.out.println("9770000000000 " + e.getMessage());
    }
    try {
      System.out.println(ISBNUtil.checkISBN("123456545"));
    } catch (Exception e) {
      System.out.println("123456545 " + e.getMessage());
    }
  }
}

class ISBNFormatException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ISBNFormatException() {
    super("ISBN Error ...");
  }

  public ISBNFormatException(String arg0) {
    super(arg0);
  }
}