/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.prim.core.AbstractApplication;
import com.prim.core.controller.ActionResult;
import com.prim.core.controller.ActionResultPrim;
import com.prim.core.controller.RightsObject;
import com.prim.web.fabric.AbstractWebFabric;
import com.prim.web.fabric.BaseFabric;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Кот
 */
abstract public class RenderAbstract implements Render{

  protected String timeInfo = "";

  @Override
  public String getTimeInfo() {
    return timeInfo;
  }
  protected AbstractWebFabric fabric = new BaseFabric();
  protected ActionResult aR = ActionResultPrim.getInstance();
  protected RightsObject rightsObject;
  protected Map<String, Object> request = new HashMap<String, Object>();
  protected Map<String, Object> session = new HashMap<String, Object>();
  protected String renderResult = "";
  protected AbstractApplication app;

  
  public RenderAbstract() {
    aR.setStatus(false);
    aR.addError("Ar is not created");
  }

  @Override
  public void setRequest(Map<String, Object> request) {
    if (request != null) {
      for (String name : request.keySet()) {
        this.request.put(name, request.get(name));
      }
    }
  }

  @Override
  public void setSession(Map<String, Object> session) {
    if (session != null) {
      for (String name : session.keySet()) {
        this.session.put(name, session.get(name));
      }
    }
  }

  @Override
  public final String getRenderResult() {
    if (renderResult == null) {
      renderResult = "";
    }
    return renderResult;
  }

  @Override
  final public AbstractWebFabric getFabric() {
    return fabric;
  }

  @Override
  final public void setFabric(AbstractWebFabric wf) {
    if (wf != null) {
      this.fabric = wf;
    }
  }

  @Override
  final public ActionResult getActionResult() {
    return aR;
  }

  @Override
  final public void setActionResult(ActionResult ar) {
    if (ar != null) {
      this.aR = ar;
    }
  }

  @Override
  final public RightsObject getRightsObject() {
    return rightsObject;
  }

  @Override
  final public void setApplication(AbstractApplication app) throws Exception {
    if (app != null) {
      this.app = app;
      this.rightsObject = app.getRightsObject();
    }
  }

  @Override
  abstract public void renderOneEntity();

  @Override
  abstract public void renderAddEntityForm();

  @Override
  abstract public void renderChangeEntityForm();

  @Override
  abstract public void renderEntityList();
}
