package com.bi.zheng.web.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class WebUtil {

  private static Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

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
