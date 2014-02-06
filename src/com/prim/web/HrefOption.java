/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.prim.support.MyString;
import com.prim.web.objects.RenderTypes;



/**
 *
 * @author кот
 */
class HrefOption implements HrefOptionInterface{
  private String object = "";
  private String action = "";
  private String specAction = "";
  private String title = "";
  private String img = "";
  private Boolean validateRights = true;
  private Integer widthImg;
  private Integer heightImg;
  private RenderTypes renderType;
  private String js = "";
  private Boolean showWithoutRights;
  private String name = "";
  private HrefOption(){
    
  }
  
  public static HrefOption getInstance(){
    HrefOption fo=new HrefOption();
    fo.setRenderType(RenderTypes.standart);
    fo.setValidateRights();
    return fo;
  }


  @Override
  public void setObject(String object) {
    this.object= MyString.getString(object);
  }

  @Override
  public void setAction(String action) {
    this.action= MyString.getString(action);
  }

  @Override
  public void setSpecAction(String specAction) {
    this.specAction= MyString.getString(specAction);
  }

  @Override
  public void setTitle(String title) {
    this.title= MyString.getString(title);
  }

  @Override
  public void setImg(String img) {
   this.img= MyString.getString(img);
  }
  


  @Override
  public void setValidateRights() {
    this.validateRights=true;
  }

  @Override
  public void setNoValidateRights() {
    this.validateRights=false;
  }

  @Override
  public void setImgParams(Integer width, Integer height) {
    if(width!=null){
      this.widthImg=width;
    }
    if(height!=null){
      this.heightImg=height;
    }
  }

  @Override
  public void setRenderType(RenderTypes rt) {
    this.renderType=rt;
  }

  @Override
  public void setJsHandler(String jsOnclick) {
    this.js=MyString.getString(jsOnclick);
  }

  @Override
  public void setShowWithoutRights(Boolean show) {
   if(show!=null){
     this.showWithoutRights=show;
   }
  }

   @Override
  public void setName(Object name) {
    if(MyString.NotNull(name)){
      this.name=MyString.getString(name);
    }
  }
  
  @Override
  public String getObject() {
    return object;
  }

  @Override
  public String getAction() {
    return action;
  }

  @Override
  public String getSpecAction() {
    return specAction;
  }

  @Override
  public String getTytle() {
    return title;
  }

  @Override
  public String getImg() {
    return img;
  }


  @Override
  public Boolean isRights() {
   return validateRights;
  }

  @Override
  public Integer getImgWidth() {
    return widthImg;
  }

  @Override
  public Integer getImgHeight() {
     return heightImg;
  }

  @Override
  public RenderTypes getRenderType() {
    return renderType;
  }

  @Override
  public String getJshandler() {
    return js;
  }

  @Override
  public boolean isShow() {
    return showWithoutRights;
  }

 

  @Override
  public String getName() {
    return name;
  }
  
  
  
  
}
