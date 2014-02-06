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
public class Tr extends AbsEntAbstract implements AbsEnt {

  public Tr() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.rowspan,EnumAttrType.head);
  }

  @Override
  public String render() {
    String result = "<tr";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += renderInnerEntities();
    result += "</tr>";

    return result;
  }
}
