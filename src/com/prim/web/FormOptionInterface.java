/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.prim.web.objects.RenderTypes;


/**
 *
 * @author кот
 */
public interface FormOptionInterface {
  /*
   * устанавливает тип формы горизонтальная. - вертикальная
   */
  public void setHorisontal(Boolean horizontal);
  
  /*
   * устанавливает объект
   */
  public void setObject(String object);
  
  /*
   * устанавливает action
   */
  public void setAction(String action);
  
   /*
   * устанавливает specAction
   */
  public void setSpecAction(String specAction);
  
  /*
   * устанавливает title
   */
  public void setTitle(String title);
  
  /*
   * устанавливает путь к кнопке
   */
  public void setImg(String Img);
  
  /*
   * Устанавливает тип формы на загрузку файлов
   */
  public void  setFormToUploadFiles(Boolean file);
  
  /*
   * Устанавливает название кнопки
   */
  public void setButtonName(String butName);
  
  /*
   * валидировать по доступу
   */
  public void setValidateRights();
  
  /*
   * не валидировать по доступу
   */
  public void setNoValidateRights();
  /*
   * уснанавливает параметры картинки - кнопки
   */
  public void setImgParams(Integer width,Integer height);
  
  /*
   * устанавливает тип рендера
   */
  public void setRenderType(RenderTypes rt);
  
  /*
   * устанавливает тип обработчика по onclick
   */
  public void setJsHandler(String jsOnclick);
  
  
  /*
   * горизонтально выводить форму или вертикально
   */
  public Boolean isHorizontal(); 
  /*
   * возвращает объект
   */
  public String getObject();
  /*
   * возвращает action
   */
  public String getAction();
  /*
   * возвращает specAction
   */
  public String getSpecAction();
  
  /*
   * возвращает title
   */
  public String getTytle();
  
  /*
   * возвращает путь с картинке
   */
  public String getImg();
  
   /*
   * возвращает работает ли форма с файлами 
   */
  public Boolean isFile();
  
  /*
   * возвращает название кнопки
   */
  public String getВuttonName();
  
  /*
   * возвращает проверять ли по правам
   */
  public Boolean isRights();
  
  
  /*
   * возвращает ширину картинки кнопки
   */
  public Integer getImgWidth();
  
  /*
   * возвращает высоту картинки кнопки
   */
  public Integer getImgHeight();
  
  /*
   * возвращает тип рендера
   */
  public RenderTypes getRenderType();
  
  /*
   * возвращает тип обработчика по onclick
   */
  public String getJshandler();
  
  
}
