/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.fabric.entities;

import com.prim.web.fabric.AbsEnt;
import com.prim.web.fabric.EnumAttrType;
import com.prim.web.fabric.EnumAttrType;

/**
 *
 * @author Кот
 */
public class Href extends AbsEntAbstract implements AbsEnt {

  public Href() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.href,EnumAttrType.title,EnumAttrType.target);
  }

  @Override
  public String render() {
    String result = "<a";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += renderInnerEntities();
    result += escapeHtml(data.getValue());
    result += "</a>";

    return result;
  }
}
