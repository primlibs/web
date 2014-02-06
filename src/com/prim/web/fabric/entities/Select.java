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
public class Select extends AbsEntAbstract implements AbsEnt {

  public Select() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.size);
  }

  @Override
  public String render() {
    String result = "<select";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += renderInnerEntities();
    result += "</select>";

    return result;
  }
}
