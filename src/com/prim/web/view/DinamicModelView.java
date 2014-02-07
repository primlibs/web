/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.view;

import com.prim.core.model.DinamicModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * экземпляр класса представляет собой одну модель, то есть одну строку из
 * результата запроса к БД. Предназначен для передачи данных в отображение 
 * 
 * @author Rice Pavel
 */
public class DinamicModelView {
  
  private Map<String, Object> params = new LinkedHashMap();
  private List<Map<String,Object>> fileArray = new ArrayList<Map<String,Object>>();

  public Map<String, Object> getParams() {
    return params;
  }

  public List<Map<String, Object>> getFileArray() {
    return fileArray;
  }
  
  public DinamicModelView(DinamicModel model) throws CloneNotSupportedException {
    params = model.getParams();
    fileArray = model.getFileArray();
  }
  
}
