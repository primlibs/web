/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prim.web.webcontroller;

import com.prim.core.AbstractApplication;
import com.prim.core.UploadedFile;
import com.prim.core.controller.ActionResult;
import com.prim.core.controller.ActionResultPrim;
import com.prim.core.controller.StatusCodes;
import com.prim.core.service.Service;
import com.prim.support.MyString;
import com.prim.web.Render;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * родительский класс для веб-контроллеров
 * 
 * @author Pavel Rice
 */
public abstract class AbstractWebController implements WebController {
  
  private final Map<String, Object> request = new HashMap();
  private final Map<String, Object> session = new HashMap();
  private final List<UploadedFile> fileList = new ArrayList();
  private final AbstractApplication app;
  
  protected final String defaultSpecAction = "";
  
  private String html;
  private boolean makeRedirect = false;
  private String redirect;
  private final Map<String, Object> redirectParams = new HashMap();

  /**
   * 
   * @param request параметры запроса
   * @param session параметры сессии
   * @param fileList загруженные файлы
   * @param app приложение
   * @throws Exception 
   */
  public AbstractWebController(Map<String, Object> request, Map<String, Object> session, List<UploadedFile> fileList, AbstractApplication app) throws Exception {
    /*
    this.request = request;
    this.session = session;
    this.fileList = fileList;
    */
    for (String key: request.keySet()) {
      Object value = request.get(key);
      this.request.put(key, value);
    }
    for (String key: session.keySet()) {
      Object value = session.get(key);
      this.session.put(key, value);
    }
    for (UploadedFile file: fileList) {
      this.fileList.add(file);
    }
    this.app = app;
  }
  
  @Override
  public final void startTransaction() throws SQLException {
    app.getConnection().setAutoCommit(false);
  }
  
  
    
  @Override
  public final void endTransaction(boolean status) throws SQLException {
    if (status) {
      app.getConnection().commit();
    } else {
      app.getConnection().rollback();
    }
    app.getConnection().setAutoCommit(true);
  }
  
  @Override
  public final Map<String, Object> getRequest() {
    Map<String, Object> newReq = new HashMap();
    for (String key: request.keySet()) {
      Object value = request.get(key);
      newReq.put(key, value);
    }
    return newReq;
  }



  @Override
  public final Map<String, Object> getSession() {
    Map<String, Object> newSession = new HashMap();
    for (String key: session.keySet()) {
      Object value = session.get(key);
      newSession.put(key, value);
    }
    return newSession;
  }


  @Override
  public final List<UploadedFile> getFileList() {
    List<UploadedFile> newList = new ArrayList();
    for (UploadedFile file: fileList) {
      newList.add(file);
    }
    return newList;
  }

  public AbstractApplication getApp() {
    return app;
  }



  @Override
  public final String getHtml() {
    return html;
  }

  /**
   * установить html
   * @param html html, который будет выведен
   */
  protected final void setHtml(String html) {
    this.html = html;
  }

  @Override
  public final boolean makeRedirect() {
    return makeRedirect;
  }

  @Override
  public final String getRedirect() {
    return redirect;
  }

  
  /**
   * установить редирект
   * @param redirectLocation редирект в формате "обект:действие"
   */
  protected final void setRedirect(String redirectLocation) {
    makeRedirect = true;
    this.redirect = redirectLocation;
  }
  
  @Override
  public final String getRedirectParamsToString() {
    String str = "";
    for (String key: redirectParams.keySet()) {
      Object value = redirectParams.get(key);
      if (value != null) {
        str += "&" + key + "=" + value;
      }
    }
    return str;
  }
  
  
  
  /**
   * установить параметр для редиректа
   * @param key название параметра
   * @param value значение параметра
   */
  protected final void setRedirectParameter(String key, Object value) {
    redirectParams.put(key, value);
  }

  /**
   * инициализировать рендер параметрами
   * @param render рендер
   * @param app приложение
   * @param request параметры из запроса
   */
  protected void initRender(Render render, AbstractApplication app, Map<String, Object> request) {
    render.setApplication(app);
    render.setRequest(request);
  }
  
  /**
   * инициализировать рендер параметрами
   * @param render рендер
   * @param app приложение
   * @param request параметры запроса
   * @param result объект ActionResult
   */
  @Deprecated
  protected void initRender(Render render, AbstractApplication app, Map<String, Object> request, ActionResult result) {
    render.setApplication(app);
    render.setRequest(request);
    render.setActionResult(result);
  }
  
  protected void initRender(Render render, ActionResult result) {
    render.setApplication(getApp());
    render.setRequest(getRequest());
    render.setActionResult(result);
  }
  
  /**
   * инициализировать сервис
   * @param service сервис
   * @param request параметры запроса
   */
  @Deprecated
  protected void initService(Service service, Map<String, Object> request) {
    service.setRequest(request);
  }
  
  protected void initService(Service service) {
    service.setRequest(getRequest());
    service.setFileList(getFileList());
  }
   
   
  
  protected String getSpecAction() {
    return MyString.getString(getRequest().get("specAction"));
  }
  
  protected boolean isTrue(ActionResult result) {
    return result.getStatus().equals(StatusCodes.TRUE);
  }
  
}
