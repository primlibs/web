/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.fabric.entities;

import com.prim.web.fabric.AbsEnt;
import com.prim.web.fabric.EnumAttrType;


/**
 *
 * @author Кот
 */
public class Ul extends AbsEntAbstract implements AbsEnt{

  public Ul() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style);
  }

  @Override
  public String render() {
    String result = "<ul";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    result += renderInnerEntities();
    result += "</ul>";

    return result;
  }
}
