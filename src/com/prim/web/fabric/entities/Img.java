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
public class Img extends AbsEntAbstract implements AbsEnt {

  public Img() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.height
            ,EnumAttrType.width,EnumAttrType.title,EnumAttrType.src);
  }

  @Override
  public String render() {
    String result = "<img";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += "/>";
    return result;
  }
}
