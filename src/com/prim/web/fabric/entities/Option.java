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
public class Option extends AbsEntAbstract implements AbsEnt {

  public Option() {
    super(EnumAttrType.id,EnumAttrType.style);
  }

  @Override
  public String render() {
    String result = "<option";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += " value=\"" + escapeHtml(data.getValue()) + "\" ";
    result += ">";
    result += renderInnerEntities();
    result += "</option>";
    return result;
  }
}
