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
public class Button extends AbsEntAbstract implements AbsEnt {

  public Button() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.maxlength
            ,EnumAttrType.size,EnumAttrType.type,EnumAttrType.src,EnumAttrType.width,EnumAttrType.title,EnumAttrType.placeholder);
  }

  @Override
  public String render() {
    String result = "<button";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + ">";
    result +=  escapeHtml(data.getValue()) + " ";
    result += "</button>";

    return result;
  }
}
