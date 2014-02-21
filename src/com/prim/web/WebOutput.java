/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.prim.web.fabric.AbsEnt;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * 
 * класс, в котором собраны статические методы для отображения
 * 
 * @author Rice Pavel
 */
public class WebOutput {
  
  private WebOutput() { }
  
  public static String escapeHtml(Object value) {
    if (value != null) {
      return StringEscapeUtils.escapeHtml4(value.toString());
    } else {
      return "";
    }
  }
  
   public static String cleanHtml(String html) {
    return Jsoup.clean(html, Whitelist.basicWithImages());
  }
  
}
