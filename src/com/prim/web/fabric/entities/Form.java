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
public class Form extends AbsEntAbstract implements AbsEnt {

  public Form() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.action,EnumAttrType.method
            ,EnumAttrType.target,EnumAttrType.height,EnumAttrType.enctype);
    data.setAttribute(EnumAttrType.action, "/");
  }

  @Override
  public String render() {
    String result = "<form";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += renderInnerEntities();
    result += escapeHtml(data.getValue());
    result += "</form>";

    return result;
  }
}
