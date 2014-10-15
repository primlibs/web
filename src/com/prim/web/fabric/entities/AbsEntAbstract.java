/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.fabric.entities;

import java.util.Collection;
import java.util.EnumMap;
import org.apache.commons.lang3.StringEscapeUtils;
import com.prim.support.MyString;
import com.prim.web.fabric.AbsEnt;
import com.prim.web.fabric.EnumAttrNoValue;
import com.prim.web.fabric.EnumAttrType;

/**
 *
 * @author Кот
 */
public class AbsEntAbstract implements AbsEnt {

  protected AbsEntData data;

  AbsEntAbstract(EnumAttrType... types) {
    data = AbsEntData.getInstance(types);
  }

  public static AbsEnt getEnt(String name) throws Exception {
    AbsEnt ae;
    name = MyString.ucFirst(name);
    try {
      ae = (AbsEnt) Class.forName("com.prim.web.fabric.entities." + name).newInstance();
    } catch (Exception eq) {
      throw new Exception("Render not found " + "com.prim.web.fabric.entities." + name + " " + eq.toString());
    }
    return ae;
  }

  @Override
  public final AbsEnt setSingleAttribute(EnumAttrNoValue singleAttribute) {
    data.setSingleAttribute(singleAttribute);
    return this;
  }

  @Override
  public final AbsEnt removeSingleAttribute(EnumAttrNoValue singleAttribute) {
    data.removeSingleAttribute(singleAttribute);
    return this;
  }

  @Override
  public final AbsEnt setAttribute(String name, String value) {
    data.setAttribute(name, value);
    return this;
  }
  
  @Override
  public final AbsEnt setAttribute(EnumAttrType type, String value) {
    data.setAttribute(type, value);
    return this;
  }

  @Override
  public final AbsEnt addAttribute(EnumAttrType type, String value) {
    data.setAttribute(type, data.getAttribute(type) + value);
    return this;
  }

  @Override
  public final AbsEnt setValue(Object o) {
    data.setValue(o);
    return this;
  }

  @Override
  public final AbsEnt setCss(Object o) {
    data.setCss(o);
    return this;
  }

  @Override
  public final String getCss() {
    return data.getCss();
  }

  @Override
  public final AbsEnt setJs(Object o) {
    data.setJs(o);
    return this;
  }

  @Override
  public final String getJs() {
    return data.getJs();
  }

  @Override
  public final EnumMap<EnumAttrType, String> getAttributesClone() {
    return data.getAttributesClone();
  }

  @Override
  public final String getAttribute(EnumAttrType type) {
    return data.getAttribute(type);
  }

  @Override
  public final AbsEnt addEnt(AbsEnt... e) {
    data.addEnt(e);
    return this;
  }

  @Override
  public final AbsEnt addEnt(Collection<AbsEnt> e) {
    data.addEnt(e);
    return this;
  }

  @Override
  public final String getValue() {
    return data.getValue();
  }

  @Override
  public final String getStringAttrs() {
    return data.getStringAttrs();
  }

  @Override
  public final String getStringSingleAttrs() {
    return data.getStringSingleAttrs();
  }

  public final String renderInnerEntities() {
    return data.renderInnerEntities();
  }

  @Override
  public String render() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public static final String escapeHtml(Object value) {
    if (value != null) {
      return StringEscapeUtils.escapeHtml4(value.toString());
    } else {
      return "";
    }
  }

  @Override
  public Boolean existInnerEntities() {
   return data.existInnerEntities();
  }

  @Override
  public AbsEnt setFor(String name) {
   data.setFor(name);
   return this;
  }
  
  
  public final String renderFor() {
    if(MyString.NotNull(data.forId)){
      return " for=\""+data.forId+"\" ";
    }
    return null;
  }

  @Override
  public AbsEnt setId(String name) {
    return addAttribute(EnumAttrType.id, name);    
  }
}
