/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.prim.core.AbstractApplication;
import com.prim.core.UploadedFile;
import com.prim.core.controller.ActionResult;
import com.prim.core.controller.ActionResultPrim;
import com.prim.core.controller.StatusCodes;
import com.prim.core.pair.Pair;
import com.prim.core.pair.Sequence;
import com.prim.core.pair.SequenceObject;
import com.prim.core.warehouse.OptionsKeeper;
import com.prim.core.warehouse.WarehouseSingleton;
import com.prim.support.MyString;
import com.prim.web.webcontroller.WebController;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Кот
 */
public class PairRunner {

  private Double processingTime = 0.00;
  private Double renderTime = 0.00;
  //Внутренняя сессия, является объектов сессии
  private Map<String, Object> innerSession = new HashMap<String, Object>();
  //переформированый запрос, проверенный на параметры, подается в приложение
  private Map<String, Object> innerRequest = new HashMap<String, Object>();
  private List<UploadedFile> fileList = new ArrayList();

  private Map<String, Object> rights = new HashMap<String, Object>();
  private Boolean xmlRenderType = false;
  private Pair pair;
  private ActionResult actionResult;
  private Boolean status = false;
  private String specialAction = "default";
  private Boolean aloneRender = false;
  private String redirect;
  private String stringRedirectParams = "";
  private AbstractApplication app;
  private Sequence sequence;
  private String renderName = "";
  private String info = "";
  private Map<String, Object> redirectParams = new HashMap<String, Object>();
  private String timeInfo = "";

  public String getTimeInfo() {
    return timeInfo;
  }

  public Double getProcessingTime() {
    return processingTime;
  }

  private void setProcessingTime(Double processingTime) {
    if (processingTime != null) {
      this.processingTime = processingTime;
    }
  }

  public Double getRenderTime() {
    return renderTime;
  }

  private void setRenderTime(Double renderTime) {
    if (processingTime != null) {
      this.renderTime = renderTime;
    }
  }

  public Double getAllTime() {
    return (processingTime + renderTime);
  }

  public Sequence getSequence() {
    if (sequence != null) {
      return sequence.clone();
    } else {
      return SequenceObject.getInstance("", "", "", "", "", "", "", "", "");
    }
  }

  public String getSpecialAction() {
    return specialAction;
  }

  public String getRenderName() {
    return renderName;
  }

  public String getInfo() {
    return info;
  }

  public String getRedirect() {
    return redirect;
  }

  public void setAloneRender(Boolean aloneRender) {
    this.aloneRender = aloneRender;
  }

  public String getStringRedirectParams() {
    return stringRedirectParams;
  }

  public PairRunner(Map<String, Object> innerSession, Map<String, Object> innerRequest, Pair pair, String doAction, AbstractApplication app, List<UploadedFile> fileList) {
    this.innerRequest = (innerRequest == null ? new HashMap<String, Object>() : innerRequest);
    this.innerSession = (innerSession == null ? new HashMap<String, Object>() : innerSession);
    this.pair = pair;
    if (doAction != null && !"".equals(doAction)) {
      this.specialAction = doAction;
    }
    this.app = app;
    if (fileList != null) {
      this.fileList = fileList;
    }
  }

  public Map<String, Object> getInnerSession() {
    return innerSession;
  }

  void setXmlRenderType() {
    xmlRenderType = true;
  }

  /*
   *  Запускает приложение возвращает строку представление
   */
  /*
   public String run() throws Exception {
   Long timeStart = new Date().getTime();
   Long timeProc = null;
   String str = "";
   //вытащить секвенцию
   if (pair == null) {
   throw new Exception("Not find pair");
   }
   Sequence seq = pair.getSequence(specialAction);
   if (seq == null) {
   seq = pair.getSequence("default");
   if (seq == null) {
   throw new Exception("Unexpected sequention");
   }
   }
   sequence = seq;
   //отработать сервис
   if (seq.getAppObjectName() != null && seq.getAppMethodName() != null && !"".equals(seq.getAppMethodName()) && !"".equals(seq.getAppObjectName())) {
   app.setRequest(innerRequest);
   app.setSession(innerSession);
   app.setFileList(fileList);
   app.objectName(seq.getAppObjectName());
   app.action(seq.getAppMethodName());
   app.processing();
   actionResult = app.getActionResult();
   //реформировать сессию
   innerSession = app.getSession();
   info = app.getInfo();
   redirectParams = app.getRedirectParams();

   timeProc = new Date().getTime();
   setProcessingTime((new Date().getTime() - timeStart) / 1000.00);

   //проверить на status ar
   if (actionResult.getStatus().equals(StatusCodes.TRUE)) {
   if (seq.getTrueRedirect() != null & !"".equals(seq.getTrueRedirect())) {
   setRedirect(seq.getTrueRedirect());
   setRedirectParamsInString(seq.getTrueRedirectParams());
   } else {
   //отработать рендер
   str = render(seq, true);
   }
   } else {
   if (seq.getFalseRedirect() != null && !"".equals(seq.getFalseRedirect())) {
   //отработать редирект
   setRedirect(seq.getFalseRedirect());
   setRedirectParamsInString(seq.getFalseRedirectParams());
   } else {
   //отработать рендер
   str = render(seq, false);
   }
   }
   } else {
   actionResult = ActionResultPrim.getInstance();
   timeProc = new Date().getTime();
   if (seq.getTrueRender() != null) {
   str = render(seq, true);
   } else if (seq.getFalseRender() != null) {
   str = render(seq, false);
   }
   }

   setRenderTime((new Date().getTime() - timeProc) / 1000.00);

   //вернуть строку   
   return str;
   }
   */
  public String run() throws Exception {

    if (!pair.isByWebController()) {

      Long timeStart = new Date().getTime();
      Long timeProc = null;
      String str = "";
      //вытащить секвенцию
      if (pair == null) {
        throw new Exception("Not find pair");
      }
      Sequence seq = pair.getSequence(specialAction);
      if (seq == null) {
        seq = pair.getSequence("default");
        if (seq == null) {
          throw new Exception("Unexpected sequention");
        }
      }
      sequence = seq;
      //отработать сервис
      if (seq.getAppObjectName() != null && seq.getAppMethodName() != null && !"".equals(seq.getAppMethodName()) && !"".equals(seq.getAppObjectName())) {
        app.setRequest(innerRequest);
        app.setSession(innerSession);
        app.setFileList(fileList);
        app.objectName(seq.getAppObjectName());
        app.action(seq.getAppMethodName());
        app.processing();
        actionResult = app.getActionResult();
        //реформировать сессию
        innerSession = app.getSession();
        info = app.getInfo();
        redirectParams = app.getRedirectParams();

        timeProc = new Date().getTime();
        setProcessingTime((new Date().getTime() - timeStart) / 1000.00);

        //проверить на status ar
        if (actionResult.getStatus().equals(StatusCodes.TRUE)) {
          if (seq.getTrueRedirect() != null & !"".equals(seq.getTrueRedirect())) {
            setRedirect(seq.getTrueRedirect());
            setRedirectParamsInString(seq.getTrueRedirectParams());
          } else {
            //отработать рендер
            str = render(seq, true);
          }
        } else {
          if (seq.getFalseRedirect() != null && !"".equals(seq.getFalseRedirect())) {
            //отработать редирект
            setRedirect(seq.getFalseRedirect());
            setRedirectParamsInString(seq.getFalseRedirectParams());
          } else {
            //отработать рендер
            str = render(seq, false);
          }
        }
      } else {
        actionResult = ActionResultPrim.getInstance();
        timeProc = new Date().getTime();
        if (seq.getTrueRender() != null) {
          str = render(seq, true);
        } else if (seq.getFalseRender() != null) {
          str = render(seq, false);
        }
      }

      setRenderTime((new Date().getTime() - timeProc) / 1000.00);

      //вернуть строку   
      return str;
    } else {
      return runbyWebController();
    }
  }

  /**
   * экспериментальный метод
   */
  private String runbyWebController() {
    String str = "";
    try {
      // создать контроллер
      OptionsKeeper opt = WarehouseSingleton.getInstance().getKeeper(app).getOptionKeeper();
      String controllerName = pair.getControllerName();
      if (controllerName != null) {
        String[] s = controllerName.split(":");
        if (s.length == 2) {
          String className = s[0];
          String methodName = s[1];
          String fullClassName = "controllers." + className;
          if (classExists(fullClassName)) {
            Class cl = Class.forName(fullClassName);
            Constructor constructor = cl.getConstructor(Map.class, Map.class, List.class, AbstractApplication.class);
            Map<String, Object> newRequest = new HashMap();            
            String spec = specialAction.trim();
            if (spec.equals("default")) {
              spec = "";
            }
            newRequest.put("specAction", spec);
            newRequest.putAll(innerRequest);
            WebController cnt = (WebController) constructor.newInstance(newRequest, innerSession, fileList, app);
            // выполнить 
            Method method = cnt.getClass().getMethod(methodName);
            method.invoke(cnt);
            // присвоить параметры
            actionResult = cnt.getActionResult();
            if (cnt.makeRedirect()) {
              setRedirect(cnt.getRedirect());
              stringRedirectParams = cnt.getRedirectParamsToString();
            } else {
              str = cnt.getHtml();
            }
          } else {
            str += "Controller not found";
          }
        } else {
          str += "Controller not found";
        }
      } else {
        str += "controllerName is null";
      }
    } catch (Exception e) {
      str = MyString.getStackExeption(e);
    }
    return str;
  }

  public static Boolean classExists(String className) {
    Boolean res = false;
    try {
      Object o = Class.forName(className);
      res = true;
    } catch (ClassNotFoundException e) {
      res = false;
    }
    return res;
  }

  public ActionResult getActionResult() {
    if (actionResult != null) {
      return actionResult;
    } else {
      return ActionResultPrim.getInstance();
    }
  }

  private String render(Sequence seq, boolean status) throws Exception {
    String renderMethod;
    String[] str;
    String result = "";
    Render wr;
    // получить renderMethod
    if (seq == null) {
      throw new Exception("Sequence is null");
    }
    if (status == true) {
      renderMethod = seq.getTrueRender();
    } else {
      renderMethod = seq.getFalseRender();
    }
    if (renderMethod != null && (str = renderMethod.split(":")).length == 2) {
      renderName = renderMethod;
      // создать объект такого класса

      try {
        Class cl;
        cl = Class.forName(app.getRenderClassPath() + "." + str[0]);
        wr = (Render) cl.newInstance();
      } catch (Exception ez) {
        throw new Exception(str[0] + str[1] + MyString.getStackExeption(ez));
      }
      // передать параметры
      wr.setRequest(innerRequest);
      wr.setSession(innerSession);
      wr.setActionResult(actionResult);
      wr.setApplication(app);
      // вызвать метод
      Method method = null;
      try {
        method = wr.getClass().getMethod(str[1]);
      } catch (Exception e) {
        throw new Exception(str[0] + str[1] + MyString.getStackExeption(e));
      }

      method.invoke(wr);
      result = wr.getRenderResult();

      timeInfo += wr.getTimeInfo();

    } else {
      throw new Exception("Render method is null ");
    }
    return result;
  }

  private void setRedirect(String str) {
    redirect = str;
  }

  private void setRedirectParamsInString(String str) {
    // заменить специальные метки в строке на значени параметров
    for (String paramName : redirectParams.keySet()) {
      Object parameter = redirectParams.get(paramName);
      if (parameter != null) {
        String parameterString = redirectParams.get(paramName).toString();
        str = str.replace(":" + paramName, parameterString);
      }
    }
    stringRedirectParams = "&" + str;
  }
}
