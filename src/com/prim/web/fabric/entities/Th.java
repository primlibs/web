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
public class Th extends AbsEntAbstract implements AbsEnt {

  public Th() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.colspan);
  }

  @Override
  public String render() {
    String result = "<th";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += renderInnerEntities();
    result += escapeHtml(data.getValue());
    result += "</th>";

    return result;
  }
}
