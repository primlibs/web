/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.fabric.entities;


/**
 *
 * @author Кот
 */
public class Txt extends AbsEntAbstract {

  public Txt() {
  }

  @Override
  public String render() {
    String result = escapeHtml(data.getValue());
    return result;
  }
}
