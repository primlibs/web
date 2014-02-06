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
public class Span extends AbsEntAbstract implements AbsEnt {

  public Span() {
    super(EnumAttrType.id,EnumAttrType.style,EnumAttrType.title);
  }

  @Override
  public String render() {
    String result = "<span";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += renderInnerEntities();
    result += escapeHtml(data.getValue());
    result += "</span>";

    return result;
  }
}
