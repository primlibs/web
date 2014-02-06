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
public class Js extends AbsEntAbstract implements AbsEnt {

  public Js() {
  }

  @Override
  public String render() {
    String result = "<script type=\"text/javascript\">";
    result += data.getValue();
    result += "</script>";
    return result;
  }
}
