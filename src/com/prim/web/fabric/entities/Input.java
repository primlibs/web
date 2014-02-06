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
public class Input extends AbsEntAbstract implements AbsEnt {

  public Input() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.maxlength
            ,EnumAttrType.size,EnumAttrType.type,EnumAttrType.src,EnumAttrType.width,EnumAttrType.title,EnumAttrType.placeholder,EnumAttrType.form);
  }

  @Override
  public String render() {
    String result = "<input";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += " value=\"" + escapeHtml(data.getValue()) + "\" ";
    result += "/>";

    return result;
  }
}
