/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.prim.core.AbstractApplication;
import com.prim.core.controller.ActionResult;
import com.prim.core.controller.RightsObject;
import com.prim.support.MyString;
import com.prim.support.filterValidator.ChainValidator;
import com.prim.web.fabric.AbsEnt;
import com.prim.web.fabric.BaseFabric;
import com.prim.web.fabric.EnumAttrNoValue;
import com.prim.web.fabric.EnumAttrType;
import com.prim.web.objects.Combo;
import com.prim.web.objects.Parameter;
import com.prim.web.objects.RenderTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author пользователь
 */
public final class FabricRender extends RenderAbstract implements Render {

  private Map<String, Combo> cashe = new LinkedHashMap<String, Combo>();
  public RenderConstant rc = new RenderConstant();
  private String baseLinkPath = "/";

  private FabricRender(Map<String, Combo> cashe, RenderConstant rc) {
    super();
    fabric = new BaseFabric();
    if (cashe != null) {
      this.cashe = cashe;
    } else {
      this.cashe = new LinkedHashMap<String, Combo>();
    }
    if (rc != null) {
      this.rc = rc;
    }
  }

  public static FabricRender getInstance(Map<String, Combo> cashe, RenderConstant rc) {
    return new FabricRender(cashe, rc);
  }

  @Override
  public void renderOneEntity() {
    String str = "";
    str = this.getClass().getName() + ":renderOneEntity";
    renderResult += str;
  }

  @Override
  public void renderAddEntityForm() {
    String str = "";
    str = this.getClass().getName() + ":renderAddEntityForm";
    renderResult += str;
  }

  @Override
  public void renderChangeEntityForm() {
    String str = "";
    str = this.getClass().getName() + ":renderChangeEntityForm";
    renderResult += str;
  }

  @Override
  public void renderEntityList() {
    String str = "";
    str = this.getClass().getName() + ":renderEntityList";
    renderResult += str;
  }

  public final AbsEnt img(String img, String width, String height, String style) throws Exception {
    AbsEnt ae = fabric.get("img");
    ae.setAttribute(EnumAttrType.src, img);
    ae.setAttribute(EnumAttrType.width, width);
    ae.setAttribute(EnumAttrType.height, height);
    ae.setAttribute(EnumAttrType.style, style);
    return ae;
  }

  /**
   * получить картинку по переданному контенту в виде строки
   *
   * @param content контент
   * @param width ширина
   * @param height высота
   * @param style стиль
   * @return
   * @throws Exception
   */
  public final AbsEnt imgByContent(Object content, String width, String height, String style) throws Exception {
    return img("data:image/gif;base64," + content, width, height, style);
  }

  public AbsEnt txt(Object value) throws Exception {
    AbsEnt txt = fabric.get("txt");
    txt.setValue(value);
    return txt;
  }

  public AbsEnt textInput(String name, Object value, String placeholder) throws Exception {
    AbsEnt ae;
    ae = fabric.get("input");
    ae.setAttribute(EnumAttrType.type, "text");
    ae.setAttribute(EnumAttrType.size, rc.TXT_INPUT_SIZE);
    ae.setAttribute(EnumAttrType.name, name);
    ae.setAttribute(EnumAttrType.placeholder, placeholder);
    ae.setValue(value);
    return ae;
  }

  public AbsEnt dateInput(String name, Object value, String placeholder) throws Exception {
    AbsEnt ae;
    ae = fabric.get("input");
    ae.setAttribute(EnumAttrType.type, "text");
    ae.setAttribute(EnumAttrType.size, rc.TXT_INPUT_SIZE);
    ae.setAttribute(EnumAttrType.name, name);
    ae.setCss("standart_datepicker");
    ae.setAttribute(EnumAttrType.placeholder, placeholder);
    ae.setValue(dateFormat(value, getRenderConstant().DT_SMALL));
    return ae;
  }

  public AbsEnt dateTimeInput(String name, Object value, String placeholder) throws Exception {
    AbsEnt ae;
    ae = fabric.get("input");
    ae.setAttribute(EnumAttrType.type, "text");
    ae.setAttribute(EnumAttrType.size, rc.TXT_INPUT_SIZE);
    ae.setAttribute(EnumAttrType.name, name);
    ae.setCss("timepicker");
    ae.removeSingleAttribute(EnumAttrNoValue.readonly);
    ae.setAttribute(EnumAttrType.placeholder, placeholder);
    ae.setValue(dateFormat(value, getRenderConstant().DT_FULL));
    return ae;
  }

  public AbsEnt passInput(String name, Object value, String placeholder) throws Exception {
    AbsEnt ae;
    ae = fabric.get("input");
    ae.setAttribute(EnumAttrType.type, "password");
    ae.setAttribute(EnumAttrType.size, rc.TXT_INPUT_SIZE);
    ae.setAttribute(EnumAttrType.name, name);
    ae.setAttribute(EnumAttrType.placeholder, placeholder);
    ae.setValue(value);
    return ae;
  }

  public AbsEnt submitInput(String name, Object value) throws Exception {
    AbsEnt ae;
    ae = fabric.get("input");
    ae.setAttribute(EnumAttrType.type, "submit");
    ae.setAttribute(EnumAttrType.size, "15");
    ae.setAttribute(EnumAttrType.name, "submit");
    ae.setAttribute(EnumAttrType.title, name);
    ae.setValue(value);
    return ae;
  }

  @Override
  public AbsEnt formSubmitImage(String title) throws Exception {
    return formSubmitImage(title, rc.SUBMITIMG_SIZE);
  }

  @Override
  public AbsEnt formSubmitImage(String title, String width) throws Exception {
    AbsEnt ae;
    ae = fabric.get("input");
    ae.setAttribute(EnumAttrType.type, "image");
    ae.setAttribute(EnumAttrType.title, title);
    ae.setAttribute(EnumAttrType.width, width);
    ae.setAttribute(EnumAttrType.name, "submit");
    ae.setValue(title);
    return ae;

  }

  public AbsEnt formSubmit(String title, String img) throws Exception {
    AbsEnt ae;
    if (img == null || img.equals("")) {
      ae = submitInput(title, title);
      ae.setValue(title);
    } else {
      ae = formSubmitImage(title);
      ae.setAttribute(EnumAttrType.src, img);
      ae.setValue(title);
    }
    return ae;
  }

  public AbsEnt hiddenInput(String name, Object value) throws Exception {
    AbsEnt ae;
    ae = fabric.get("input");
    ae.setAttribute(EnumAttrType.type, "hidden");
    ae.setAttribute(EnumAttrType.name, name);
    ae.setValue(value);
    return ae;
  }

  public AbsEnt fileInput(String name, Object value, String placeholder) throws Exception {
    AbsEnt ae;
    ae = fabric.get("input");
    ae.setAttribute(EnumAttrType.type, "file");
    ae.setAttribute(EnumAttrType.size, rc.TXT_INPUT_SIZE);
    ae.setAttribute(EnumAttrType.name, name);
    ae.setAttribute(EnumAttrType.placeholder, placeholder);
    ae.setValue(value);
    return ae;
  }

  public AbsEnt checkBox(String name, Boolean checked, String id) throws Exception {
    AbsEnt ch;
    ch = fabric.get("input");
    ch.setAttribute(EnumAttrType.type, "checkbox").setAttribute(EnumAttrType.id, id);
    ch.setAttribute(EnumAttrType.name, name);
    ch.setValue(1);
    if (checked == true) {
      ch.setJs(" " + ch.getJs() + " checked ");
    }
    return ch;
  }

  public AbsEnt checkBox(String name, Boolean checked, Object value, String id) throws Exception {
    AbsEnt ch;
    ch = fabric.get("input");
    ch.setAttribute(EnumAttrType.type, "checkbox").setAttribute(EnumAttrType.id, id);
    ch.setAttribute(EnumAttrType.name, name);
    ch.setValue(value);
    if (checked == true) {
      ch.setJs(" " + ch.getJs() + " checked ");
    }
    return ch;
  }

  public AbsEnt checkBox(String name, Object ob) throws Exception {
    if (MyString.NotNull(ob)) {
      return checkBox(name, Boolean.TRUE, null);
    } else {
      return checkBox(name, Boolean.FALSE, null);
    }
  }

  public AbsEnt combo(Map<String, Object> map, Object value, String name) throws Exception {
    AbsEnt select;
    select = fabric.get("select");
    select.setAttribute(EnumAttrType.name, name);
    for (String st : map.keySet()) {
      AbsEnt option = fabric.get("option");
      option.setValue(st);
      AbsEnt txt = fabric.get("txt");
      txt.setValue(map.get(st));
      option.addEnt(txt);

      if (value != null && value.toString().equals(st)) {
        option.setJs("selected");
      }
      select.addEnt(option);
    }
    return select;
  }

  /**
   * возвращает элемент формы - выпадающий список
   *
   * @param map массив параметров
   * @param value значение
   * @param name название
   * @param mandatory является ли обязательным
   * @return
   * @throws Exception
   */
  public AbsEnt combo(Map<String, Object> map, Object value, String name, boolean mandatory) throws Exception {
    Map<String, Object> params = new LinkedHashMap();
    if (!mandatory) {
      params.put("", "не выбрано");
    }
    params.putAll(map);
    return combo(params, value, name);
  }

  public AbsEnt textArea(String name, Object ob, String placeholder) throws Exception {
    return textArea(name, ob, null, null, placeholder);
  }

  public AbsEnt textArea(String name, Object ob, Object rows, Object cols, String placeholder) throws Exception {
    AbsEnt ae;
    ae = fabric.get("textArea");
    ae.setAttribute(EnumAttrType.name, name);
    ae.setValue(ob);
    ae.setAttribute(EnumAttrType.placeholder, placeholder);
    ae.setAttribute(EnumAttrType.rows, MyString.getString(rows));
    ae.setAttribute(EnumAttrType.cols, MyString.getString(cols));
    return ae;

  }

  public String dateFormat(Object ob, String format) {
    String res = "";
    if (ob != null) {
      ob.toString();
      ChainValidator cv = new ChainValidator();
      HashMap<String, Object> hs = new HashMap<String, Object>();
      hs.put("format", format);
      cv.addChain("DateToFormatFilter", hs);
      cv.execute(ob);
      if (cv.getData() != null) {
        res = cv.getData().toString();
      }
    }
    return res;
  }

  /**
   *
   * @param service название класса сервиса (из bi)
   * @param method название метода из сервиса
   * @param value искомый ключ
   * @return информацмя соответствующая ключу
   */
  public String comboLikeString(String service, String method, Object value) {
    Combo cb = getCombo(service, method);
    if (value != null && !value.toString().equals("0")) {
      Object res = cb.getComboList().get(value.toString().trim());
      if (res != null) {
        return res.toString();
      } else {
        return value.toString();
      }
    } else {
      return "";
    }
  }

  public AbsEnt combo(String service, String method, Object value, String name) throws Exception {
    Combo cb = getCombo(service, method);
    return combo(cb.getComboList(), value, name);
  }

  public AbsEnt combo(String service, String method, Object value, String name, boolean mandatory) throws Exception {
    Combo cb = getCombo(service, method);
    Map<String, Object> map = new LinkedHashMap();
    if (!mandatory) {
      map.put("0", "не выбрано");
    }
    map.putAll(cb.getComboList());
    return combo(map, value, name);
  }

  public AbsEnt combo(String service, String method, Object value, String name, String addName, String addValue) throws Exception {
    Combo cb = getCombo(service, method);
    Map<String, Object> map = new LinkedHashMap();
    if (MyString.NotNull(addName)) {
      if (MyString.NotNull(addValue)) {
        map.put(addValue, addName);
      } else {
        map.put("", addName);
      }
    }
    map.putAll(cb.getComboList());
    return combo(map, value, name);
  }

  public Combo getCombo(String service, String method) {
    Combo cb = null;
    // если в кэше нет объекта, который соответствует этому методу
    if (cashe.get(service + method) == null) {
      cb = new Combo(service, method, app);
      cashe.put(service + method, cb);
    } else {
      cb = cashe.get(service + method);
    }
    return cb;
  }

  public AbsEnt div(String id, Object value, String css, String javaScript) throws Exception {
    AbsEnt ae;
    ae = fabric.get("div");
    ae.setAttribute(EnumAttrType.id, id);
    ae.setCss(css);
    ae.setJs(javaScript);
    if (value instanceof AbsEnt) {
      ae.addEnt((AbsEnt) value);
    } else {
      ae.setValue(value);
    }
    return ae;
  }

  public String phoneNumberFt(Object number, String base) {
    String newft = "";
    if (number != null) {
      newft = number.toString();
      if (newft.length() == 11) {
        char[] nearr = {newft.charAt(1), newft.charAt(2), newft.charAt(3), newft.charAt(4)};

        char[] one = {newft.charAt(0)};
        char[] three1 = {newft.charAt(1), newft.charAt(2), newft.charAt(3)};
        char[] three2 = {newft.charAt(4), newft.charAt(5), newft.charAt(6)};
        char[] two = {newft.charAt(7), newft.charAt(8)};
        char[] two2 = {newft.charAt(9), newft.charAt(10)};
        String prefix = new String(nearr);
        String res;
        if (prefix.equals(base)) {
          res = new String(three2) + "-" + new String(two) + "-" + new String(two2);
        } else {
          res = new String(one) + " (" + new String(three1) + ") " + new String(three2) + "-" + new String(two) + "-" + new String(two2);
        }
        newft = res;
      }
    }
    return newft;
  }

  public AbsEnt table(String id, String style, String border, String cellpadding, String sellspacing, String javaScript) throws Exception {
    AbsEnt ae;
    ae = fabric.get("table");
    ae.setAttribute(EnumAttrType.id, id);
    ae.setAttribute(EnumAttrType.style, style);
    ae.setJs(javaScript);
    ae.setAttribute(EnumAttrType.cellpadding, cellpadding);
    ae.setAttribute(EnumAttrType.cellspacing, sellspacing);
    ae.setAttribute(EnumAttrType.border, border);
    ae.setAttribute(EnumAttrType.id, id);
    return ae;
  }

  public AbsEnt table(String border, String cellpadding, String sellspacing) throws Exception {
    return table(null, null, border, cellpadding, sellspacing, null);
  }

  /**
   * вывод числа в "денежном" формате
   *
   * @param data
   * @return
   */
  public String renderDecimal(Object data) {
    if (data != null) {
      ChainValidator chain = new ChainValidator();
      chain.addChain("DecimalFilter");
      chain.execute(data);
      return chain.getData().toString();
    } else {
      return "";
    }
  }

  public AbsEnt td(String inner) throws Exception {
    return td(null, inner);
  }

  public AbsEnt td(AbsEnt tr, Object inner) throws Exception {
    AbsEnt td = fabric.get("td");
    if (inner instanceof AbsEnt) {
      AbsEnt inn = (AbsEnt) inner;
      td.addEnt(inn);
    } else {
      td.setValue(inner);
    }
    if (tr != null) {
      tr.addEnt(td);
    }
    return td;
  }

  public AbsEnt th(String inner) throws Exception {
    return th(null, inner);
  }

  public AbsEnt th(AbsEnt tr, Object inner) throws Exception {
    AbsEnt td = fabric.get("th");
    if (inner instanceof AbsEnt) {
      AbsEnt inn = (AbsEnt) inner;
      td.addEnt(inn);
    } else {
      td.setValue(inner);
    }
    if (tr != null) {
      tr.addEnt(td);
    }
    return td;
  }

  public AbsEnt tr(Object... inners) throws Exception {
    return tr(null, inners);
  }

  public AbsEnt tr(AbsEnt table, Object... inners) throws Exception {
    AbsEnt tr = fabric.get("tr");
    for (Object obj : inners) {
      td(tr, obj);
    }
    if (table != null) {
      table.addEnt(tr);
    }
    return tr;
  }

  public AbsEnt trTh(AbsEnt table, Object... inners) throws Exception {
    AbsEnt tr = fabric.get("tr").addAttribute(EnumAttrType.head, "1");
    for (Object obj : inners) {
      th(tr, obj);
    }
    if (table != null) {
      table.addEnt(tr);
    }
    return tr;
  }

  public AbsEnt span(String id, String style, Object value, String javaScript, String css) throws Exception {
    AbsEnt ae;
    ae = fabric.get("span");
    ae.setAttribute(EnumAttrType.id, id);
    ae.setAttribute(EnumAttrType.style, style);
    ae.setCss(css);
    ae.setJs(javaScript);
    ae.setValue(value);
    return ae;
  }

  public AbsEnt span(String id, String style, Object value, String javaScript) throws Exception {
    return span(null, style, value, null, null);
  }

  public AbsEnt span(String style, Object value) throws Exception {
    return span(null, style, value, null);
  }

  public AbsEnt span(String style, String css, Object value) throws Exception {
    return span(null, style, value, null, css);
  }

  /**
   * \
   * Создает форму для загрузки файло и кнопку для добавления подобных, связано
   * со скриптом в index.java
   *
   * @return html код в виде сущности (форма для загрузки(
   * @throws Exception
   */
  public AbsEnt multipleFileForm() throws Exception {
    AbsEnt filesDiv = fabric.get("div");
    filesDiv.addAttribute(EnumAttrType.id, "files");

    AbsEnt file1Div = fabric.get("div");
    file1Div.addAttribute(EnumAttrType.id, "file1");

    AbsEnt input = fabric.get("Input");
    input.addAttribute(EnumAttrType.id, "userfile1");
    input.setCss("uploadFile");
    input.addAttribute(EnumAttrType.type, "file");
    input.setJs("onchange='fileAdd(2)'");
    input.addAttribute(EnumAttrType.size, "80");
    input.addAttribute(EnumAttrType.name, "file1[]");
    input.setSingleAttribute(EnumAttrNoValue.multiple);
    file1Div.addEnt(input);
    filesDiv.addEnt(file1Div);

    return (filesDiv);
  }

  public AbsEnt li(AbsEnt obj) throws Exception {
    AbsEnt li = getFabric().get("li");
    if (obj != null) {
      li.addEnt(obj);
      return li;
    }
    return null;
  }

  public AbsEnt label(String forName, String value, String css, String javascript) throws Exception {
    AbsEnt label = getFabric().get("label");
    if (label != null) {
      label.setFor(forName);
      label.setValue(value);
      label.setJs(javascript);
      label.setCss(css);
      return label;
    }
    return null;
  }

  public AbsEnt div(String style, Object value) throws Exception {
    AbsEnt res = div(null, value, null, null);
    if (res != null) {
      res.setAttribute(EnumAttrType.style, style);
    }
    return res;
  }

  /**
   * безопасно добавляет css
   */
  public AbsEnt addCss(AbsEnt ae, String css) {
    if (ae != null) {
      ae.setCss(css);
    }
    return ae;
  }

  /**
   * безопасно добавляет стиль
   */
  public AbsEnt addStyle(AbsEnt ae, String style) {
    if (ae != null) {
      ae.setAttribute(EnumAttrType.style, style);
    }
    return ae;
  }

  /**
   * регистрирует Exception, то есть записывает в renderResult полный стек
   * объекта Exception
   *
   * @param exc объект Exception
   */
  public void registerException(Exception exc) {
    renderResult += MyString.getStackExeption(exc);
  }

  @Override
  public void addRenderResult(String str) {
    renderResult += str;
  }

  @Override
  public Object getReq(String name) {
    return request.get(name);
  }
  
  public Map<String, Object> getRequestClone() {
    Map<String, Object> map = new HashMap();
    for (String key: request.keySet()) {
      map.put(key, request.get(key));
    }
    return map;
  }

  @Override
  public Object getSess(String name) {
    return session.get(name);
  }

  @Override
  public RightsObject getRights() {
    return RightsObject.valueOf(rightsObject);
  }

  @Override
  public ActionResult getAr() {
    return aR;
  }

  @Override
  public RenderConstant getRenderConstant() {
    return rc;
  }

  @Override
  public AbstractApplication getApp() {
    return app;
  }

  /**
   * возвращает select с множественным выбором
   *
   * @param map массив значений
   * @param value выбранное значение, либо массив выбранных значений, либо null
   * @param name параметр name в select
   * @param size параметр size. Может быть null
   * @return
   * @throws Exception
   */
  public AbsEnt multipleCombo(Map<String, Object> map, Object value, String name, Integer size) throws Exception {
    AbsEnt select;
    select = fabric.get("select");
    select.setAttribute(EnumAttrType.name, name);
    select.setSingleAttribute(EnumAttrNoValue.multiple);
    if (size != null) {
      select.setAttribute(EnumAttrType.size, size.toString());
    }

    List<String> valuesList = new ArrayList();
    if (value != null) {
      try {
        valuesList = Arrays.asList((String[]) value);
      } catch (Exception e) {
        String param = value.toString();
        valuesList.add(param);
      }
    }

    for (String st : map.keySet()) {
      AbsEnt option = fabric.get("option");
      option.setValue(st);
      AbsEnt txt = fabric.get("txt");
      txt.setValue(map.get(st));
      option.addEnt(txt);

      if (valuesList.contains(st)) {
        option.setJs("selected");
      }
      select.addEnt(option);
    }
    return select;
  }

  @Override
  public AbsEnt multipleCombo(String service, String method, Object value, String name, Integer size) throws Exception {
    Combo cb = getCombo(service, method);
    return multipleCombo(cb.getComboList(), value, name, size);
  }

  @Override
  public Boolean setBaseLinkPath(String path) {
    baseLinkPath = MyString.getString(path);
    return true;
  }

  @Override
  public String getBaseLinkPath() {
    return baseLinkPath;
  }

  @Override
  public AbsEnt p(String id, Object value, String css, String javaScript) throws Exception {
    AbsEnt ae = fabric.get("p");
    ae.setAttribute(EnumAttrType.id, id);
    ae.setCss(css);
    ae.setJs(javaScript);
    if (value instanceof AbsEnt) {
      ae.addEnt((AbsEnt) value);
    } else {
      ae.setValue(value);
    }
    return ae;
  }

  @Override
  public AbsEnt form(Boolean formToUploadFiles) throws Exception {
    AbsEnt ae;
    ae = fabric.get("form");
    if (formToUploadFiles != null && formToUploadFiles) {
      ae.setAttribute(EnumAttrType.enctype, "multipart/form-data");
    }
    ae.setAttribute(EnumAttrType.method, "POST");
    ae.setAttribute(EnumAttrType.action, MyString.getString(baseLinkPath));
    return ae;
  }

  @Deprecated
  public AbsEnt horizontalForm(Map<AbsEnt, String> inner, String title, String img, Boolean formToUploadFiles, String buttonName) throws Exception {
    FormOption fo = FormOption.getInstance();
    fo.setTitle(title);
    fo.setImg(img);
    fo.setFormToUploadFiles(formToUploadFiles);
    fo.setButtonName(buttonName);
    return horizontalForm(inner, fo);
  }

  @Deprecated
  public AbsEnt horizontalForm(Map<AbsEnt, String> inner, String title, String img) throws Exception {
    return horizontalForm(inner, title, img, null, null);
  }

  @Deprecated
  public AbsEnt verticalForm(Map<AbsEnt, String> inner, String title, String img, Boolean formToUploadFiles, String buttonName) throws Exception {
    FormOption fo = FormOption.getInstance();
    fo.setTitle(title);
    fo.setImg(img);
    fo.setFormToUploadFiles(formToUploadFiles);
    fo.setButtonName(buttonName);
    return verticalForm(inner, fo);
  }

  @Deprecated
  public AbsEnt verticalForm(Map<AbsEnt, String> inner, String title, String img) throws Exception {
    return verticalForm(inner, title, img, null, null);
  }

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img, Boolean validateRights, String ajax, Boolean formToUploadFiles, String buttonName) throws Exception {
    FormOptionInterface fo = getFormOption();
    fo.setObject(object);
    fo.setAction(action);
    fo.setSpecAction(specAction);
    fo.setHorisontal(horizontal);
    fo.setImg(img);
    fo.setButtonName(buttonName);
    if (MyString.NotNull(ajax)) {
      fo.setRenderType(RenderTypes.ajax);
      fo.setJsHandler("onsubmit=\"return submitForm(this,'" + ajax + "');\"");
    }
    fo.setButtonName(buttonName);
    fo.setFormToUploadFiles(formToUploadFiles);
    fo.setTitle(title);
    if (validateRights == true) {
      fo.setValidateRights();
    } else {
      fo.setNoValidateRights();
    }
    return rightForm(inner, fo);
  }

  /**
   * Возвращает стандартную форму с кнопкой в виде картинки
   *
   * @param horizontal
   * @param object
   * @param action
   * @param specAction
   * @param inner
   * @param title
   * @return
   * @throws Exception
   */
  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title) throws Exception {
    return rightForm(horizontal, object, action, specAction, inner, title, null, true, null, false, title);
  }

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img, Boolean validateRights, String ajax, Boolean formToUploadFiles) throws Exception {
    return rightForm(horizontal, object, action, specAction, inner, title, img, validateRights, ajax, formToUploadFiles, null);
  }

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img, Boolean validateRights, String ajax) throws Exception {
    return rightForm(horizontal, object, action, specAction, inner, title, img, validateRights, ajax, null);
  }

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img, Boolean validateRights) throws Exception {
    return rightForm(horizontal, object, action, specAction, inner, title, img, validateRights, null, null);
  }

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img) throws Exception {
    return rightForm(horizontal, object, action, specAction, inner, title, img, true, null, null);
  }

  /**
   * возвращает форму, которая подходит для загрузки файлов
   *
   * @param horizontal
   * @param object
   * @param action
   * @param specAction
   * @param inner
   * @param title
   * @param img
   * @return
   * @throws Exception
   */
  public AbsEnt rightFormUploadFile(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img) throws Exception {
    return rightForm(horizontal, object, action, specAction, inner, title, img, true, null, true);
  }

  /**
   * Возвращает стандартную AJAX-форму с кнопкой в виде картинки
   *
   * @param horizontal
   * @param object
   * @param action
   * @param specAction
   * @param inner
   * @param title
   * @param img
   * @return
   * @throws Exception
   */
  @Deprecated
  public AbsEnt standartAjaxForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img) throws Exception {
    FormOption fo = FormOption.getInstance();
    fo.setHorisontal(horizontal);
    fo.setObject(object);
    fo.setAction(action);
    fo.setSpecAction(specAction);
    fo.setImg(img);
    fo.setRenderType(RenderTypes.ajax);
    fo.setJsHandler("onsubmit=\"return submitForm(this,'" + getRenderConstant().STANDART_FLOAT_ID + "');\"");
    return rightForm(inner, fo);
  }

  /**
   * Возвращает стандартную AJAX-форму без картинки, с кнопкой button
   *
   * @param horizontal
   * @param object
   * @param action
   * @param specAction
   * @param inner
   * @param buttonName
   * @return
   * @throws Exception
   */
  @Deprecated
  public AbsEnt standartAjaxForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String buttonName) throws Exception {
    FormOption fo = FormOption.getInstance();
    fo.setHorisontal(horizontal);
    fo.setObject(object);
    fo.setAction(action);
    fo.setSpecAction(specAction);
    fo.setButtonName(buttonName);
    fo.setRenderType(RenderTypes.ajax);
    fo.setJsHandler("onsubmit=\"return submitForm(this,'" + getRenderConstant().STANDART_FLOAT_ID + "');\"");
    return rightForm(inner, fo);
  }

  @Deprecated
  public AbsEnt standartFileUploadForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img) throws Exception {
    return rightForm(horizontal, object, action, specAction, inner, title, img, true, null, true);
  }

  public AbsEnt rightForm(Map<AbsEnt, String> inner, FormOptionInterface fo) throws Exception {
    AbsEnt ae = getFabric().get("txt");
    Boolean rend = true;
    if (fo.isRights()) {
      rend = false;
      if (getRights().methodInRight(fo.getObject(), fo.getAction()) == true) {
        rend = true;
      }
    }
    if (rend == true) {
      if (fo.isHorizontal() == true) {
        ae = horizontalForm(inner, fo);
      } else {
        ae = verticalForm(inner, fo);
      }
      ae.addEnt(hiddenInput("action", fo.getAction()));
      ae.addEnt(hiddenInput("object", fo.getObject()));
      ae.addEnt(hiddenInput("specAction", fo.getSpecAction()));
      ae.setAttribute(EnumAttrType.style, "float:left;padding:0;");
      ae.setJs(fo.getJshandler());
      ae.addEnt(hiddenInput("renderType", fo.getRenderType()));
    }
    return ae;
  }

  private AbsEnt horizontalForm(Map<AbsEnt, String> inner, FormOptionInterface fo) throws Exception {
    AbsEnt ae = form(fo.isFile());
    AbsEnt table = getFabric().get("table");
    ae.addEnt(table);
    AbsEnt tr = getFabric().get("tr");
    table.addEnt(tr);
    if (inner != null) {
      for (AbsEnt aee : inner.keySet()) {
        if (aee.getAttribute(EnumAttrType.type).equals("hidden")) {
          tr.addEnt(aee);
        } else {
          AbsEnt td1 = getFabric().get("td");
          td1.setValue(inner.get(aee));
          tr.addEnt(td1);
          AbsEnt td = getFabric().get("td");
          td.addEnt(aee);
          tr.addEnt(td);
        }
      }
    }
    if (!MyString.NotNull(fo.getВuttonName())) {
      AbsEnt td = getFabric().get("td");
      AbsEnt formSumbit = formSubmit(fo.getTytle(), fo.getImg());
      if (fo.getImgWidth() != null) {
        formSumbit.setAttribute(EnumAttrType.width, fo.getImgWidth().toString());
      }
      td.addEnt(formSumbit);
      tr.addEnt(td);
    } else {
      AbsEnt td = getFabric().get("td");
      td.addEnt(getFabric().get("button").setValue(fo.getВuttonName()));
      tr.addEnt(td);
    }
    ae.addEnt(hiddenInput("submit", "submit"));
    return ae;
  }

  private AbsEnt verticalForm(Map<AbsEnt, String> inner, FormOptionInterface fo) throws Exception {
    AbsEnt ae = form(fo.isFile());
    AbsEnt table = getFabric().get("table");
    ae.addEnt(table);
    if (inner != null) {
      for (AbsEnt aee : inner.keySet()) {
        if (aee.getAttribute(EnumAttrType.type).equals("hidden")) {
          table.addEnt(aee);
        } else {
          AbsEnt tr = getFabric().get("tr");
          table.addEnt(tr);
          AbsEnt td1 = getFabric().get("td");
          td1.setValue(inner.get(aee));
          tr.addEnt(td1);
          AbsEnt td = getFabric().get("td");
          td.addEnt(aee);
          tr.addEnt(td);
        }
      }
    }
    AbsEnt tr = getFabric().get("tr");
    table.addEnt(tr);
    if (!MyString.NotNull(fo.getВuttonName())) {
      AbsEnt td = getFabric().get("td");
      AbsEnt formSumbit = formSubmit(fo.getTytle(), fo.getImg());
      if (fo.getImgWidth() != null) {
        formSumbit.setAttribute(EnumAttrType.width, fo.getImgWidth().toString());
      }
      td.addEnt(formSumbit);
      tr.addEnt(td);
    } else {
      AbsEnt td = getFabric().get("td");
      AbsEnt td1 = getFabric().get("td");
      td.addEnt(getFabric().get("button").setValue(fo.getВuttonName()));
      tr.addEnt(td1, td);
    }
    ae.addEnt(hiddenInput("submit", "submit"));
    return ae;
  }

  @Override
  public FormOptionInterface getFormOption() {
    return FormOption.getInstance();
  }

  /**
   * возвращает стандартную Ajax-ссылку
   *
   * @param object
   * @param action
   * @param specAction
   * @param params
   * @param name
   * @return
   * @throws Exception
   * 
   */
  @Deprecated
  public final AbsEnt standartAjaxHref(String object, String action, String specAction, Map<String, Object> params, Object name) throws Exception {
    HrefOptionInterface ho=HrefOption.getInstance();
    ho.setObject(object);
    ho.setAction(action);
    ho.setSpecAction(specAction);
    ho.setName(name);
    ho.setRenderType(RenderTypes.ajax);
    ho.setJsHandler("onClick=\"return submitGet(this,'" + rc.STANDART_FLOAT_ID + "')\"");
    return href(params, ho);
  }

  @Deprecated
  public final AbsEnt href(String object, String action, String specAction, List<Parameter> params, Object name, Boolean validateRights, String ajax, Boolean showTxtWithoutRights) throws Exception {
    HrefOptionInterface ho=HrefOption.getInstance();
    ho.setObject(object);
    ho.setAction(action);
    ho.setSpecAction(specAction);
    ho.setName(name);
    ho.setShowWithoutRights(showTxtWithoutRights);
    if(validateRights!=null){
      if(validateRights==true){
        ho.setValidateRights();
      }else{
        ho.setValidateRights();
      }
    }
    if(MyString.NotNull(ajax)){
      ho.setRenderType(RenderTypes.ajax);
      ho.setJsHandler("onClick=\"return submitGet(this,'" + ajax + "')\"");
    }
    return href(params, ho);
  }

  @Deprecated
  public final AbsEnt href(String object, String action, String SpecAction, List<Parameter> params, Object name) throws Exception {
    return href(object, action, SpecAction, params, name, true, null, false);
  }

  @Deprecated
  public final AbsEnt href(String object, String action, String specAction, Map<String, Object> params, Object name, Boolean validateRights, String ajax, Boolean showTxtWithoutRights) throws Exception {
    HrefOptionInterface ho=HrefOption.getInstance();
    ho.setObject(object);
    ho.setAction(action);
    ho.setSpecAction(specAction);
    ho.setName(name);
    ho.setShowWithoutRights(showTxtWithoutRights);
    if(validateRights!=null){
      if(validateRights==true){
        ho.setValidateRights();
      }else{
        ho.setNoValidateRights();
      }
    }
    if(MyString.NotNull(ajax)){
      ho.setRenderType(RenderTypes.ajax);
      ho.setJsHandler("onClick=\"return submitGet(this,'" + ajax + "')\"");
    }
    if (params == null) {
      params = new HashMap();
    }
    return href(params, ho);
  }

  @Deprecated
  public final AbsEnt href(String object, String action, String SpecAction, Map<String, Object> params, Object name, Boolean validateRights) throws Exception {
    return href(object, action, SpecAction, params, name, validateRights, null, false);
  }

  @Deprecated
  public final AbsEnt href(String object, String action, String SpecAction, Map<String, Object> params, Object name) throws Exception {
    return href(object, action, SpecAction, params, name, true, null, false);
  }

  @Override
  public AbsEnt href(Map<String, Object> params, HrefOptionInterface ho)  throws Exception {
    return href(Parameter.getArray(params), ho);
  }

  @Override
  public AbsEnt href(List<Parameter> params, HrefOptionInterface ho) throws Exception  {
    AbsEnt ae = null;
    Boolean rend = true;
    if (ho.isRights() == true) {
      rend = false;      
      if (rightsObject.methodInRight(ho.getObject(), ho.getAction()) == true) {
        rend = true;
      }
    }
    if (rend == true) {
      if (params == null) {
        params = new ArrayList();
      }
      ae = fabric.get("href");
      String href = "?";
      if (ho.getAction()!= null) {
        href += "&action=" + fabric.escapeHtml(ho.getAction());
      }
      if (ho.getObject()!= null) {
        href += "&object=" + fabric.escapeHtml(ho.getObject());
      }
      if (ho.getSpecAction() != null) {
        href += "&specAction=" + fabric.escapeHtml(ho.getSpecAction());
      }
      for (Parameter param : params) {
        href += "&" + param.getName() + "=" + fabric.escapeHtml(param.getValue());
      }
      if (ho.getRenderType()==RenderTypes.ajax) {
        href += "&renderType=ajax";
        ae.setJs(ho.getJshandler());
      }
      if (ho.getTytle() != null && !ho.getTytle().isEmpty()) {
        ae.setAttribute(EnumAttrType.title, ho.getTytle());
      }
      ae.setAttribute(EnumAttrType.href, MyString.getString(baseLinkPath) + href);
      ae.setValue(ho.getName());
    } else if (ho.isShow()) {
      ae = fabric.get("txt");
      ae.setValue(ho.getName());
    }
    return ae;
  }

  @Override
  public HrefOptionInterface getHrefOption() {
    return HrefOption.getInstance();
  }
  
  @Override
  public AbsEnt getImgByContent(String content, String width, String height, String style) throws Exception {
    return img("data:image/gif;base64," + content, width, height, style);
  }
  
}
