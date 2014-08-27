/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.fabric.entities;

import com.prim.support.MyString;
import com.prim.web.fabric.AbsEnt;
import com.prim.web.fabric.EnumAttrType;
import com.prim.web.fabric.EnumAttrType;

/**
 *
 * @author Кот
 */
public class Table extends AbsEntAbstract implements AbsEnt {

  private String head="";
  private String body="";
  public Table() {
    super(EnumAttrType.id,EnumAttrType.name,EnumAttrType.style,EnumAttrType.cellpadding
            ,EnumAttrType.cellspacing,EnumAttrType.border);
  }

  @Override
  public String render() {
    String result = "<table";
    result += getStringAttrs();
    result += getStringSingleAttrs();
    result += " " + data.getJs() + " ";
    result += ">";
    if(!data.entities.isEmpty()){
      for(AbsEnt ae:data.entities){
        if(MyString.NotNull(ae.getAttribute(EnumAttrType.head))){
          head+=ae.render();
        }else{
          body+=ae.render();
        }
      }
    }
    result +="<thead>"+head+"</thead>";
    result +="<tbody>"+body+"</tbody>";
    result += "</table>";

    return result;
  }  
}
