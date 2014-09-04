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

  public void add() {
    String specAction = MyString.getString(request.get("specAction"));
    if (specAction.equals(defaultSpecAction)) {
      render.setRequest(request);
      render.renderAddEntityForm();
      setHtml(render.getRenderResult());
    } else if (specAction.equals("add")) {
      service.setRequest(request);
      service.saveModel();
      ActionResult result = service.getActionResult();
      setActionResult(result);
      if (isTrue(result)) {
        setRedirect(objectName + ":showOne");
        addRedirectParameter(primaryName, result.get(primaryName));
      } else {
        render.setActionResult(result);
        render.renderAddEntityForm();
        setHtml(render.getRenderResult());
      }
    }
  }

  public void change() {
    String specAction = MyString.getString(request.get("specAction"));
    if (specAction.equals(defaultSpecAction)) {
      render.renderChangeEntityForm();
      setHtml(render.getRenderResult());
    } else if (specAction.equals("change")) {
      service.setRequest(request);
      service.updateModel();
      ActionResult result = service.getActionResult();
      setActionResult(result);
      if (isTrue(result)) {
        setRedirect(objectName + ":showOne");
        addRedirectParameter(primaryName, result.get(primaryName));
      } else {
        render.setActionResult(result);
        render.renderChangeEntityForm();
        setHtml(render.getRenderResult());
      }
    }
  }

  public void delete() {
    String specAction = MyString.getString(request.get("specAction"));
    if (specAction.equals(defaultSpecAction)) {
      service.closeModel();
      ActionResult result = service.getActionResult();
      setActionResult(result);
      setRedirect(objectName + ":showOne");
      addRedirectParameter(primaryName, result.get(primaryName));
    }
  }
  
  public void search() {
    String specAction = MyString.getString(request.get("specAction"));
    if (specAction.equals(defaultSpecAction)) {
      service.findActive();
      ActionResult result = service.getActionResult();
      setActionResult(result);
      render.setActionResult(result);
      render.renderEntityList();
      setHtml(render.getRenderResult());
    }
  }
  
  public void showOne() {
    String specAction = MyString.getString(request.get("specAction"));
    if (specAction.equals(defaultSpecAction)) {
      service.searchById();
      ActionResult result = service.getActionResult();
      setActionResult(result);
      render.setActionResult(result);
      render.renderOneEntity();
      setHtml(render.getRenderResult());
    }
  }

  private boolean isTrue(ActionResult result) {
    return result.getStatus().equals(StatusCodes.TRUE);
  }

}
