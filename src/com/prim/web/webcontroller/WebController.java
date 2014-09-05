/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web.webcontroller;

import com.prim.core.UploadedFile;
import com.prim.core.controller.ActionResult;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Pavel Rice
 */
public interface WebController {

    public static String[] internalMethods = {"getRequest", "getSession", "getFileList", "getHtml", "makeRedirect", "getRedirectParamsToString"
    , "getRedirect", "getActionResult", "wait", "toString", "equals", "getClass", "hashCode", "notify", "notifyAll", "startTransaction", "endTransaction"};
    
    
  
  public Map<String, Object> getRequest();

  public Map<String, Object> getSession();

  public List<UploadedFile> getFileList();

  public String getHtml();

  public boolean makeRedirect();

  public String getRedirectParamsToString();

  public String getRedirect();
  
  
  public  void startTransaction() throws SQLException ;
  
  public void endTransaction(boolean status) throws SQLException;
  
}
