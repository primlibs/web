/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.webclient;

import com.prim.core.UploadedFile;
import com.prim.core.controller.RightsObject;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author User
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
