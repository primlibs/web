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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pavel Rice
 */
public class StandartWebController extends AbstractWebController {

  private Service service;
  private Render render;
  private String objectName;
  private String primaryName;

  private final String defaultSpecAction = "";

  public StandartWebController(Map<String, Object> request, Map<String, Object> session, List<UploadedFile> fileList, AbstractApplication app, Service service, Render render, String objectName, String primaryName) throws Exception {
    super(request, session, fileList, app);
    this.service = service;
    this.service.setRequest(request);
    this.render = render;
    this.render.setApplication(app);
    this.render.setRequest(request);
    // TODO в конструктор рендера добавить Application
    try {
      this.render.setApplication(app);
    } catch (Exception e) {

    }
    this.objectName = objectName;
    this.primaryName = primaryName;
  }

  public ActionResult add() {
    String specAction = MyString.getString(request.get("specAction"));
    ActionResult result = null;
    if (specAction.equals(defaultSpecAction)) {
      render.setRequest(request);
      render.renderAddEntityForm();
      setHtml(render.getRenderResult());
    } else if (specAction.equals("add")) {
      service.setRequest(request);
      service.saveModel();
      result = service.getActionResult();
      if (isTrue(result)) {
        setRedirect(objectName + ":showOne");
        setRedirectParameter(primaryName, result.get(primaryName));
      } else {
        render.setActionResult(result);
        render.renderAddEntityForm();
        setHtml(render.getRenderResult());
      }
    }
    return result;
  }

  public ActionResult change() {
    ActionResult result = null;
    String specAction = MyString.getString(request.get("specAction"));
    if (specAction.equals(defaultSpecAction)) {
      render.renderChangeEntityForm();
      setHtml(render.getRenderResult());
    } else if (specAction.equals("change")) {
      service.setRequest(request);
      service.updateModel();
      result = service.getActionResult();
      if (isTrue(result)) {
        setRedirect(objectName + ":showOne");
        setRedirectParameter(primaryName, result.get(primaryName));
      } else {
        render.setActionResult(result);
        render.renderChangeEntityForm();
        setHtml(render.getRenderResult());
      }
    }
    return result;
  }

  public ActionResult delete() {
    String specAction = MyString.getString(request.get("specAction"));
    ActionResult result = null;
    if (specAction.equals(defaultSpecAction)) {
      service.closeModel();
      result = service.getActionResult();
      setRedirect(objectName + ":showOne");
      setRedirectParameter(primaryName, result.get(primaryName));
    }
    return result;
  }
  
  public ActionResult search() {
    String specAction = MyString.getString(request.get("specAction"));
    ActionResult result = null;
    if (specAction.equals(defaultSpecAction)) {
      service.findActive();
      result = service.getActionResult();
      render.setActionResult(result);
      render.renderEntityList();
      setHtml(render.getRenderResult());
    }
    return result;
  }
  
  public ActionResult showOne() {
    String specAction = MyString.getString(request.get("specAction"));
    ActionResult result = null;
    if (specAction.equals(defaultSpecAction)) {
      service.searchById();
      result = service.getActionResult();
      render.setActionResult(result);
      render.renderOneEntity();
      setHtml(render.getRenderResult());
    }
    return result;
  }

  private boolean isTrue(ActionResult result) {
    return result.getStatus().equals(StatusCodes.TRUE);
  }

}
