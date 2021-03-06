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
public class TextArea extends AbsEntAbstract implements AbsEnt {

  public TextArea() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.cols
            ,EnumAttrType.rows,EnumAttrType.placeholder,EnumAttrType.form);
  }

  @Override
  public String render() {
    String result = "<textarea";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += escapeHtml(data.getValue());
    result += "</textarea>";

    return result;
  }
}
