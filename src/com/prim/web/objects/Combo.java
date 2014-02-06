/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.objects;

import com.prim.core.AbstractApplication;
import com.prim.core.controller.ActionResult;
import com.prim.core.service.Service;
import com.prim.core.service.ServiceFactory;
import com.prim.support.MyString;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//import prim.warehouse.OptionsSingleton;

/**
 * позволяет вставить комбо в форму
 *
 * @author Pavel Rice
 */
final public class Combo implements Serializable {

  static final long serialVersionUID = 12345L;
  /**
   * название сервиса
   */
  private String serviceName;
  /**
   * название метода
   */
  private String methodName;
  /**
   * данные - результаты запроса в БД
   */
  private Map<String, Object> comboList = new LinkedHashMap<String, Object>();
  /**
   * данные, которые пришли из запроса пользователя
   */
  private Map<String, Object> request = new HashMap<String, Object>();
  
  private String error="";
  
  
  /**
   * установить тип вывода - комбо
   *
   * @param serviceName - имя сервиса, к которому нужно обратиться
   * @param methodName - метод сервиса, который нужно вызвать
   * @param parameterValueName - имя параметра, значение которого будет указано
   * в атрибуте value тега option
   * @param parameterContentName - имя параметра, значение которого будет внутри
   * тега option
   */
  public Combo(String serviceName, String methodName) {
    this.serviceName = serviceName;
    this.methodName = methodName;
  }
  
  public Combo(String serviceName, String methodName,AbstractApplication app) {
    this.serviceName = serviceName;
    this.methodName = methodName;
    createComboList(app);
  }

  public Combo(Map<String, Object> arr) {
    comboList = arr;
  }

  public Map<String, Object> getComboList() {
    return comboList;
  }

  public String getServiceName() {
    return serviceName;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setRequest(HashMap<String, Object> request) {
    this.request = request;
  }

  public String getError() {
    return error;
  }

  
  public void createComboList(AbstractApplication app) {
    if (comboList.isEmpty()) {
      Map<String, Object> optionsCombo = new LinkedHashMap<String, Object>();
      try {
        Service service = ServiceFactory.service(serviceName, app);
        Method actionMethod = service.getClass().getMethod(methodName);
        service.setRequest(request);
        actionMethod.invoke(service);
        // получить результат
        ActionResult actionResult = service.getActionResult();
        Map<String, Object> params = actionResult.getDictionary();
        for (String key : params.keySet()) {
          optionsCombo.put(key, params.get(key));
        }
        error=actionResult.getErrors().toString();
      } catch (Exception e) {
        error=MyString.getStackExeption(e);
      }
      comboList = optionsCombo;
    }
  }
}
