/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.fabric.entities;

import java.util.EnumMap;
import com.prim.web.fabric.AbsEnt;
import com.prim.web.fabric.AbstractWebFabric;
import com.prim.web.fabric.EnumAttrType;
import com.prim.web.fabric.EnumAttrType;

/**
 *
 * @author Кот
 */
class NotEscapeText extends AbsEntAbstract implements AbsEnt {

  public NotEscapeText() {
    //super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.height,EnumAttrType.width);
  }

  @Override
  public String render() {
    String result = data.getValue();
    return result;
  }

}
