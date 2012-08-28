package com.bi.zheng.web.util;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:bizheng@gmail.com">Zheng BI</a>
 * @version $Id: $
 */
public class CharUtil {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  // 字符串匹配的算法.
  public String getMaxMatch(String a, String b) {
    StringBuffer tmp = new StringBuffer();
    String maxString = "";
    int max = 0;
    int len = 0;
    char[] aArray = a.toCharArray();
    char[] bArray = b.toCharArray();
    int posA = 0;
    int posB = 0;
    while (posA < aArray.length - max) {
      posB = 0;
      while (posB < (bArray.length - max)) {
        if (aArray[posA] == bArray[posB]) {
          len = 1;
          tmp = new StringBuffer();
          tmp.append(aArray[posA]);
          while ((posA + len < aArray.length) && (posB + len < bArray.length)
                  && (aArray[posA + len] == bArray[posB + len])) {
            tmp.append(aArray[posA + len]);
            len++;
          }
          if (len > max) {
            max = len;
            maxString = tmp.toString();
          }
        }
        posB++;
      }
      posA++;
    }
    return maxString;
  }

  /**
   * 全角字符转半角字符
   * 
   * @param QJStr
   * @return String
   */
  public static final String QJToBJChange(String QJStr) {
    char[] chr = QJStr.toCharArray();
    String str = "";
    for (int i = 0; i < chr.length; i++) {
      chr[i] = (char) ((int) chr[i] - 65248);
      str += chr[i];
    }
    return str;
  }

  /**
   * 去掉字符串中重复的子字符串
   * 
   * @param str
   * @return String
   */
  private static String removeSameString(String str) {
    Set<String> mLinkedSet = new LinkedHashSet<String>();
    String[] strArray = str.split(" ");
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < strArray.length; i++) {
      if (!mLinkedSet.contains(strArray[i])) {
        mLinkedSet.add(strArray[i]);
        sb.append(strArray[i] + " ");
      }
    }
    System.out.println(mLinkedSet);
    return sb.toString().substring(0, sb.toString().length() - 1);
  }

  // 过滤特殊字符
  public static String encoding(String src) {
    if (src == null)
      return "";
    StringBuilder result = new StringBuilder();
    if (src != null) {
      src = src.trim();
      for (int pos = 0; pos < src.length(); pos++) {
        switch (src.charAt(pos)) {
        case '\"':
          result.append("&quot;");
          break;
        case '<':
          result.append("&lt;");
          break;
        case '>':
          result.append("&gt;");
          break;
        case '\'':
          result.append("&apos;");
          break;
        case '&':
          result.append("&amp;");
          break;
        case '%':
          result.append("&pc;");
          break;
        case '_':
          result.append("&ul;");
          break;
        case '#':
          result.append("&shap;");
          break;
        case '?':
          result.append("&ques;");
          break;
        default:
          result.append(src.charAt(pos));
          break;
        }
      }
    }
    return result.toString();
  }

  // 反过滤特殊字符
  public static String decoding(String src) {
    if (src == null)
      return "";
    String result = src;
    result = result.replace("&quot;", "\"").replace("&apos;", "\'");
    result = result.replace("&lt;", "<").replace("&gt;", ">");
    result = result.replace("&amp;", "&");
    result = result.replace("&pc;", "%").replace("&ul", "_");
    result = result.replace("&shap;", "#").replace("&ques", "?");
    return result;
  }
  
//  匹配中文字符的正则表达式： [\u4e00-\u9fa5]
  
  public static boolean hasChineseCharacter(String str) {
    Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
    Matcher matcher = pattern.matcher(str);
    return matcher.matches();
  }
}
