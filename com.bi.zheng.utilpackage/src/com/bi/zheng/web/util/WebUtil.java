package com.bi.zheng.web.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

/**
 * @author <a href="mailto:bizheng@gmail.com">Zheng BI</a>
 * @version $Id: $
 */
public class WebUtil {

  private final static Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

//  private final static Pattern ipPattern = Pattern.compile("192\\.168\\.(?:25[0-5]|2[0-4]\\d|\\d|[1-9]\\d|1\\d\\d)\\.(?:25[0-5]|2[0-4]\\d|\\d|[1-9]\\d|1\\d\\d");

  /*
   * 比较IP大小 ，s<e返回-，s>e返回+，否则返回0
   */
  public static int compare(String sIP, String eIP) {

    String[] s1 = sIP.split("\\.");
    String[] e1 = eIP.split("\\.");

    for (int i = 0; i < s1.length; i++) {

      if (Integer.parseInt(s1[i]) < Integer.parseInt(e1[i])) {
        return -1;
      } else if (Integer.parseInt(s1[i]) > Integer.parseInt(e1[i])) {
        return 1;
      } else {
        continue;
      }
    }
    return 0;
  }

//  /*
//   * 判断IP是否合法
//   */
//  public static boolean is_IP(String ip) {
//    Matcher m = ipPattern.matcher(ip);
//    return m.matches();
//  }

  public static boolean isEmail(String email) {
    return emailPattern.matcher(email).matches();
  }

  public static List<String> getEmailListFromText(String text) {

    List<String> emailList = Lists.newArrayList();
    Matcher matchr = emailPattern.matcher(text);
    while (matchr.find()) {
      String email = matchr.group();
      emailList.add(email);
    }
    return emailList;
  }

}
