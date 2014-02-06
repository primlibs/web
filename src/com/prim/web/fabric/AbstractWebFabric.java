/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.fabric;

import org.apache.commons.lang3.StringEscapeUtils;
import com.prim.web.fabric.entities.AbsEntAbstract;

/**
 *
 * @author Кот
 */
public abstract class AbstractWebFabric {

  protected AbstractWebFabric() {
  }

  public final AbsEnt get(String name) throws Exception {
    return AbsEntAbstract.getEnt(name);
  }

  public static final String escapeHtml(Object value) {
    if (value != null) {
      return StringEscapeUtils.escapeHtml4(value.toString());
    } else {
      return "";
    }
  }
}
