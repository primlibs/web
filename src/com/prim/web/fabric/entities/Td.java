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
public class Td extends AbsEntAbstract implements AbsEnt {

  public Td() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.colspan
            ,EnumAttrType.width,EnumAttrType.height, EnumAttrType.rowspan, EnumAttrType.title);
  }

  @Override
  public String render() {
    String result = "<td";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += renderInnerEntities();
    result += escapeHtml(data.getValue());
    result += "</td>";

    return result;
  }
}
