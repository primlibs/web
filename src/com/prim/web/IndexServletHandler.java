/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.google.gson.Gson;
import com.prim.core.AbstractApplication;
import com.prim.core.controller.ActionResult;
import com.prim.core.db.ExecutorFabric;
import com.prim.core.db.QueryExecutor;
import com.prim.core.pair.Pair;
import com.prim.core.warehouse.pair.PairKeeper;
import com.prim.support.MyString;
import com.prim.web.fabric.BaseFabric;
import com.prim.web.objects.Combo;
import com.prim.web.webclient.StandartWebClientImpl;
import com.prim.web.webclient.WebClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * обработчик сервлета index
 *
 * @author Rice Pavel
 */
public abstract class IndexServletHandler {

  protected String message = "";
  protected Map<String, Combo> cashe = new LinkedHashMap<String, Combo>();
  protected String tm;
  private String info;
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected AbstractApplication app;
  protected WebClient webClient;

  public IndexServletHandler(HttpServletRequest request, HttpServletResponse response, String message, Map<String, Combo> cashe) {
    this.request = request;
    this.response = response;
    this.message = message;
    this.cashe = cashe;
  }

  /**
   * Processes requests for both HTTP
   * <code>GET</code> and
   * <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  public void processRequest()
          throws ServletException, IOException, Exception {

    // инициализация
    message = "";
    info = "";
    List<pairTime> timeList = new ArrayList<pairTime>();
    request.setCharacterEncoding("UTF-8");
    LinkedHashMap<String, String> hs = new LinkedHashMap<String, String>();
    cashe = new LinkedHashMap<String, Combo>();

    // время
    tm = "";
    double diff;
    Date date;
    date = new Date();
    long time1 = date.getTime();

    PrintWriter out = null;
    try {
      // инициализация
      app = createApplication();
      webClient = createWebClient(app);
      QueryExecutor execStart = ExecutorFabric.getExecutor(app.getConnection(), "");
      execStart.resetMaxQueryInfo();

      // если пользователь хочет выйти
      if (webClient.getInnerRequest().get("logout") != null && webClient.getInnerRequest().get("logout").equals("logout")) {
        request.getSession().removeAttribute("key");
        request.getSession().removeAttribute("cron");
        request.getSession().removeAttribute("system");
      }

      // тип вывода
      boolean ajax = false;
      if (webClient.getRenderType().equals("ajax")) {
        ajax = true;
      }

      // проверка авторизации и установка массива прав
      Object key = getSecurityKey();
      if (checkAuthorization(app, key) && setRights(app, key, request.getRemoteAddr())) {

        setDefaultPair(webClient);

        // получить пары, которые должны выполниться
        List<Pair> pairs = searchPairs(app, webClient, ajax);

        // отсортировать пары
        List<Pair> newPairs = sortPairs(pairs);

        // считаем время выполнения пар
        date = new Date();
        long time2 = date.getTime();
        diff = (time2 - time1) / 1000.00;
        tm += "1. " + diff + " c. </br>";

        String redirect = "";
        String redirectParams = "";
        //String errors = "";
        List<String> errors = new ArrayList();
        String messages = "";
        //String messageList = "";
        List<String> messageList = new ArrayList();
        // выполнить каждую пару
        pairCycle:
        for (Pair p : newPairs) {

          // параметры сессии
          Map<String, Object> sess = getSessionParameters();

          // если есть права на пару
          if (!p.getObject().equals("app") && app.getRightsObject().methodInRight(p.getObject(), p.getAction())) {
            // запустить PaiRunner, выполнить пару, получить результат, записать результат в массив
            PairRunner pr = new PairRunner(sess, webClient.getInnerRequest(), p, webClient.getDoAction(), app, webClient.getUploadedFile());

            // если нужно отдать файл
            if (p.getAction().equals("getFile")) {
              // то отдать файл
              messages += "file :" + getFile(pr, response);
            }

            hs.put(p.getObject() + ":" + p.getAction(), pr.run());

            // записать в сессию параметры
            for (String sname : pr.getInnerSession().keySet()) {
              request.getSession().setAttribute(sname, pr.getInnerSession().get(sname));
            }

            // записать информацию о выполении
            writePairInfo(pr, p, app);
            tm += "<br/>" + pr.getTimeInfo() + "<br/>";

            // если в сессии есть ошибки и message, то вывести их. Это применяется в случае редиректа
            if (request.getSession().getAttribute(p.getObject() + "_error") != null && ajax == false) {
              messages += request.getSession().getAttribute(p.getObject() + "_error").toString();
              request.getSession().removeAttribute(p.getObject() + "_error");
            }
            if (request.getSession().getAttribute("message") != null && ajax == false) {
              messages += request.getSession().getAttribute("message").toString();
              request.getSession().removeAttribute("message");
            }

            errors.addAll(pr.getActionResult().getErrors());
            //messageList += pr.getActionResult().getMessageList();
            messageList.addAll(pr.getActionResult().getMessageList());

            // если редирект
            if (isRedirect(pr)) {
              if (ajax) {
                redirect = pr.getRedirect();
                redirectParams = pr.getStringRedirectParams();
                break pairCycle;
              } else {
                if (app != null) {
                  app.closeConnection();
                }
                if (out != null) {
                  out.close();
                }
                redirect(pr, p.getObject(), ajax);
              }
            }

            // информация о времени выполнения
            timeList.add(new pairTime(p.getObject() + ":" + p.getAction() + " processing- ", pr.getProcessingTime()));
            timeList.add(new pairTime(p.getObject() + ":" + p.getAction() + " render- ", pr.getRenderTime()));
          }

        }

        String htmlResult = renderHash(hs);

        // вывести ajax
        if (ajax) {
          printAjax(htmlResult, messages, redirect, redirectParams, messageList, errors);
          if (app != null) {
            app.closeConnection();
          }
          if (out != null) {
            out.close();
          }
          return;
        }

        // время
        long time3 = new Date().getTime();

        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();

        if (webClient.getRenderType().equals("doc")) {
          // вывести документ
          printDoc(out, htmlResult);
        } else {
          // вывести html
          printDefault(out, app, htmlResult, messages);
        }
        //если не авторизован
      } else {
        // то перенаправить
        if (app != null) {
          app.closeConnection();
        }
        if (out != null) {
          out.close();
        }
        redirectIfNoAuthorization();
      }

    } catch (Exception e) {
      response.setContentType("text/html;charset=UTF-8");
      if (out == null) {
        out = response.getWriter();
      }
      e.printStackTrace(out);
    } finally {
      if (app != null) {
        app.closeConnection();
      }
      if (out != null) {
        out.close();
      }
    }

  }

  protected WebClient createWebClient(AbstractApplication app) throws Exception {
    return new StandartWebClientImpl(request, app.getKeeper().getOptionKeeper());
  }

  protected abstract void redirectIfNoAuthorization() throws Exception;

  protected void setDefaultPair(WebClient webClient) {
  }

  protected Object getSecurityKey() {
    return request.getSession().getAttribute("key");
  }

  protected abstract boolean checkAuthorization(AbstractApplication app, Object key) throws Exception;

  protected abstract boolean setRights(AbstractApplication app, Object key, String requestIp) throws Exception;

  protected List<Pair> searchPairs(AbstractApplication app, WebClient wc, boolean ajax) {
    PairKeeper ps = app.getKeeper().getPairKeeper();

    // получить пары, которые должны выполниться
    List<Pair> pairs = ps.getPairList(wc.getActiveObjects(), wc.getActiveAction());

    // если RenderType == ajax, то получить пару и её дочерние пары, а родительские не получать
    if (ajax) {
      //Pair pair = ps.searchOnePair(wc.getActiveObjects(), wc.getActiveAction());
      //pairs.add(pair);
      pairs = ps.getChildrenPairList(wc.getActiveObjects(), wc.getActiveAction());
    }
    return pairs;
  }

  protected List<Pair> sortPairs(List<Pair> pairs) {
    List<Pair> newPair = new ArrayList<Pair>();
    int cnt = pairs.size() - 1;
    while (cnt >= 0) {
      newPair.add(pairs.get(cnt));
      cnt--;
    }
    return newPair;
  }

  protected boolean isRedirect(PairRunner pairRunner) {
    return (pairRunner.getRedirect() != null && !pairRunner.getRedirect().isEmpty());
  }

  private void printAjax(String htmlResult, String messages, String redirect, String redirectParams, List<String> messageList, List<String> errors) throws Exception {
    PrintWriter out = null;
    try {
      String ajaxOutput = getAjaxOutput(htmlResult, messages, redirect, redirectParams, messageList, errors);
      response.setContentType("text/html;charset=UTF-8");
      out = response.getWriter();
      out.println(ajaxOutput);
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  protected abstract String getAjaxOutput(String htmlResult, String messages, String redirect, String redirectParams, List<String> messageList, List<String> errors) throws Exception;

  private void printDoc(PrintWriter out, String htmlResult) throws Exception {
    String docOutput = getDocOutput(htmlResult);
    out.println(docOutput);
  }

  protected abstract String getDocOutput(String htmlResult) throws Exception;

  private void printDefault(PrintWriter out, AbstractApplication app, String htmlResult, String messages) throws Exception {
    String htmlOutput = getDefaultOutput(htmlResult, app, messages);
    out.println(htmlOutput);
  }

  protected abstract String getDefaultOutput(String htmlResult, AbstractApplication app, String messages) throws Exception;

  protected Map<String, Object> getSessionParameters() {
    HashMap<String, Object> sess = new HashMap<String, Object>();
    Enumeration eNames = request.getSession().getAttributeNames();
    while (eNames.hasMoreElements()) {
      String attributeName = (String) eNames.nextElement();
      if (!attributeName.equals("key") && !attributeName.equals("cron")
              && !attributeName.equals("system")) {
        sess.put(attributeName, request.getSession().getAttribute(attributeName));
      }
    }
    return sess;
  }

  protected void writePairInfo(PairRunner pr, Pair p, AbstractApplication app) {
    info += "Pair: <b>" + p.getObject() + ":" + p.getAction() + "</b>, SpecAction: <b>"
            + pr.getSpecialAction()
            + "</b> ,Controller: <b>" + pr.getSequence().getAppObjectName() + ":" + pr.getSequence().getAppMethodName()
            + "</b> ,Render: <b>" + pr.getRenderName() + "</b></br>";
    info += pr.getInfo();
    info += "</br>";
    info += p.getAction();
    info += "-" + p.getObject();
    info += "-" + app.getRightsObject().methodInRight(p.getObject(), p.getAction());
  }

  /**
   * вывод html
   *
   * @param hs
   * @return
   */
  protected String renderHash(HashMap<String, String> hs) {
    String result = "";
    Boolean in = false;
    for (String key : hs.keySet()) {
      if (in == true) {
        result = result.replace("{" + key + "}", hs.get(key));
      } else {
        result += hs.get(key);
      }
      in = true;
    }
    // замена лишних тегов. 
    result = result.replaceAll("\\{[\\w:]+\\}", "");
    return result;
  }

  /**
   * редирект
   *
   * @param request
   * @param resp
   * @param pr
   * @param obj
   * @param ajax
   * @throws IOException
   * @throws Exception
   */
  protected void redirect(PairRunner pr, Object obj, boolean ajax) throws IOException, Exception {
    if (pr.getActionResult().getStatus() == false && ajax == false) {
      request.getSession().setAttribute(obj + "_error", renderErrors(pr.getActionResult(), obj));
    }

    if (ajax == false) {
      request.getSession().setAttribute("message", renderMessages(pr.getActionResult(), obj));
    }

    String redirectUrl = getRedirectUrl(pr.getRedirect(), pr.getStringRedirectParams());

    response.sendRedirect(redirectUrl);
  }

  protected abstract String renderErrors(ActionResult result, Object obj) throws Exception;

  protected abstract String renderMessages(ActionResult result, Object obj) throws Exception;

  protected abstract String getRedirectUrl(String redirect, String redirectParams);

  /**
   * отдать файл
   *
   * @param app
   * @param object
   * @param action
   * @param req
   * @param response
   * @param request
   * @return
   * @throws Exception
   */
  protected String getFile(PairRunner pr, HttpServletResponse response) throws Exception {
    pr.run();
    ActionResult ar = pr.getActionResult();
    String str = "status " + ar.getStatus() + " error " + ar.getErrors();
    FileInputStream fileIn = null;
    ServletOutputStream out = null;
    try {
      if (ar.getStatus() == true) {

        String newName = ar.get("fileName").toString().replace('\"', '\'').replace(" ", "_");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;filename=\"" + MyString.transliterate(newName) + "\"");
        out = response.getOutputStream();

        Object path = ar.get("filePath");
        Object content = ar.get("content");

        str += "path " + path;
        str += "content " + (content != null);

        byte[] contentBytes;
        if (content != null) {
          contentBytes = (byte[]) ar.get("content");
          out.write(contentBytes);
        } else if (path != null) {
          File file = new File(path.toString());
          String length = String.valueOf(file.length());
          fileIn = new FileInputStream(file);
          int readBytes = 0;
          byte[] buffer = new byte[4096];
          try {
            while ((readBytes = fileIn.read(buffer, 0, 4096)) != -1) {
              out.write(buffer, 0, readBytes);
            }
          } catch (Exception e) {
            message += MyString.getStackExeption(e);
          }
        }
      } else {
        str += ar.getErrors().toString();
      }
    } finally {
      if (fileIn != null) {
        fileIn.close();
      }
      if (out != null) {
        out.flush();
        out.close();
      }
    }
    return str;
  }

  protected abstract AbstractApplication createApplication() throws Exception;
}

class pairTime implements Comparable<pairTime> {

  Object name = null;
  Double value = null;

  pairTime(Object name, Double value) {
    this.name = name;
    this.value = value;
  }

  public Object getName() {
    return name;
  }

  public Double getValue() {
    return value;
  }

  @Override
  public int compareTo(pairTime o) {
    return o.getValue().compareTo(this.getValue());
  }
}
