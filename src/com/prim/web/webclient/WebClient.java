/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.webclient;

import com.prim.core.UploadedFile;
import java.util.HashMap;
import java.util.List;

/**
 * отвечает за прием запроса от Web, сохранение параметров запроса
 * @author Rice Pavel
 */
public interface WebClient {
  
  public static final String WEB="web";
  public static final String JSON="json";
  public static final String CLASSIC_JSON = "classic_json";
  
  /**
   * возвращает массив с параметрами, которые пришли в приложение
   * @return 
   */
  public HashMap<String, Object> getInnerRequest();

  /**
   * 
   * @return параметр specAction из запроса
   */
  public String getDoAction();

  /**
   * 
   * @return параметр object из запроса
   */
  public String getActiveObjects();

  public void setActiveAction(String activeAction);
  
  public void setActiveObjects(String activeObjects);
  
  /**
   * 
   * @return параметр action из запроса
   */
  public String getActiveAction();

  /**
   * 
   * @return тип отображения
   */
  public String getRenderType();

  /**
   * 
   * @return массив загруженных файлов
   */
  public List<UploadedFile> getUploadedFile();
  
}
