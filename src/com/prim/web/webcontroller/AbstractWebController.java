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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pavel Rice
 */
public abstract class AbstractWebController implements WebController {
  
  protected Map<String, Object> request = new HashMap();
  protected Map<String, Object> session = new HashMap();
  protected List<UploadedFile> fileList = new ArrayList();
  protected AbstractApplication app;
  

  
  private String html;
  private boolean makeRedirect = false;
  private String redirect;
  private Map<String, Object> redirectParams = new HashMap();

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
  
  public final void startTransaction() throws SQLException {
    app.getConnection().setAutoCommit(false);
  }
  
  public final void endTransaction(boolean status) throws SQLException {
    if (status) {
      app.getConnection().commit();
    } else {
      app.getConnection().rollback();
    }
    app.getConnection().setAutoCommit(true);
  }
  
  public final Map<String, Object> getRequest() {
    return request;
  }



  public final Map<String, Object> getSession() {
    return session;
  }


  public final List<UploadedFile> getFileList() {
    return fileList;
  }



  public final String getHtml() {
    return html;
  }

  protected final void setHtml(String html) {
    this.html = html;
  }

  public final boolean makeRedirect() {
    return makeRedirect;
  }

  public final String getRedirect() {
    return redirect;
  }

  
  
  protected final void setRedirect(String redirectLocation) {
    makeRedirect = true;
    this.redirect = redirectLocation;
  }
  
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
  
  protected final void setRedirectParameter(String key, Object value) {
    redirectParams.put(key, value);
  }


  
}
