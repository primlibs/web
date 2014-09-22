/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.prim.core.AbstractApplication;
import com.prim.core.controller.ActionResult;
import com.prim.core.controller.RightsObject;
import com.prim.web.fabric.AbsEnt;
import com.prim.web.fabric.AbstractWebFabric;
import com.prim.web.objects.Combo;
import com.prim.web.objects.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author кот
 */
public interface Render {

  /*
   * вернет базовый путь до ссылок
   */
  public String getBaseLinkPath();

  /*
   * стандартный метод просмотра одной сущности
   */
  public void renderOneEntity();
  
  /*
   * стандартный метод - показать форму добавления
   */
  public void renderAddEntityForm();
  /*
   * стандартный метод - показать форму изменения
   */
  public void renderChangeEntityForm();
  /*
   * стандартный метод - показать список
   */
  public void renderEntityList();

  public String getTimeInfo();

  public void setRequest(Map<String, Object> request);

  public void setSession(Map<String, Object> session);

  public String getRenderResult();

  public AbstractWebFabric getFabric();

  public void setFabric(AbstractWebFabric wf);

  public ActionResult getActionResult();

  public void setActionResult(ActionResult ar);

  public RightsObject getRightsObject();

  public void setApplication(AbstractApplication app);

  public AbsEnt img(String img, String width, String height, String style) throws Exception;

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
  public AbsEnt imgByContent(Object content, String width, String height, String style) throws Exception;

  public AbsEnt txt(Object value) throws Exception;

  /*
   * базовая реализация формы, возвращает только объект формы, без полей, разметки, таблиц
   */
  public AbsEnt form(Boolean formToUploadFiles) throws Exception;

  /**
   * возвращает объект, включающий в себя html, который не экранируется.
   * @param html 
   * @return
   * @throws Exception 
   */
  public AbsEnt notExcapeHtml(String html) throws Exception;
  
  public AbsEnt textInput(String name, Object value, String placeholder) throws Exception;

  public AbsEnt dateInput(String name, Object value, String placeholder) throws Exception;

  public AbsEnt dateTimeInput(String name, Object value, String placeholder) throws Exception;

  public AbsEnt passInput(String name, Object value, String placeholder) throws Exception;

  public AbsEnt submitInput(String name, Object value) throws Exception;

  public AbsEnt formSubmitImage(String title) throws Exception;

  public AbsEnt formSubmitImage(String title, String valImg) throws Exception;

  public AbsEnt formSubmit(String title, String img) throws Exception;

  public AbsEnt hiddenInput(String name, Object value) throws Exception;

  public AbsEnt fileInput(String name, Object value, String placeholder) throws Exception;

  public AbsEnt checkBox(String name, Boolean checked, String id) throws Exception;

  public AbsEnt checkBox(String name, Boolean checked, Object value, String id) throws Exception;

  public AbsEnt checkBox(String name, Object ob) throws Exception;

  public AbsEnt combo(Map<String, Object> map, Object value, String name) throws Exception;

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
  public AbsEnt combo(Map<String, Object> map, Object value, String name, boolean mandatory) throws Exception;

  public AbsEnt textArea(String name, Object ob, String placeholder) throws Exception;

  public AbsEnt textArea(String name, Object ob, Object rows, Object cols, String placeholder) throws Exception;

  public String dateFormat(Object ob, String format);

  /**
   *
   * @param service название класса сервиса (из bi)
   * @param method название метода из сервиса
   * @param value искомый ключ
   * @return информацмя соответствующая ключу
   */
  public String comboLikeString(String service, String method, Object value);

  public AbsEnt combo(String service, String method, Object value, String name) throws Exception;

  public AbsEnt combo(String service, String method, Object value, String name, boolean mandatory) throws Exception;

  public AbsEnt combo(String service, String method, Object value, String name, String addName, String addValue) throws Exception;

  public Combo getCombo(String service, String method);

  public AbsEnt div(String id, Object value, String css, String javaScript) throws Exception;

  /**
   * форматирует строковую запись номера в человекопонятную, базу убирает
   * @param number 
   * @param base
   * @return 
   */
  public String phoneNumberFt(Object number, String base);

  public AbsEnt table(String id, String style, String border, String cellpadding, String sellspacing, String javaScript) throws Exception;

  public AbsEnt table(String border, String cellpadding, String sellspacing) throws Exception;

  public Map<String, Object> getRequestClone();

  /**
   * вывод числа в "денежном" формате
   *
   * @param data
   * @return
   */
  public String renderDecimal(Object data);

  public AbsEnt td(String inner) throws Exception;

  public AbsEnt td(AbsEnt tr, Object inner) throws Exception;

  public AbsEnt th(String inner) throws Exception;

  public AbsEnt th(AbsEnt tr, Object inner) throws Exception;

  public AbsEnt tr(Object... inners) throws Exception;

  public AbsEnt tr(AbsEnt table, Object... inners) throws Exception;

  public AbsEnt trTh(AbsEnt table, Object... inners) throws Exception;

  public AbsEnt span(String id, String style, Object value, String javaScript, String css) throws Exception;

  public AbsEnt span(String id, String style, Object value, String javaScript) throws Exception;

  public AbsEnt span(String style, Object value) throws Exception;

  public AbsEnt span(String style, String css, Object value) throws Exception;

  /**
   * 
   * Создает форму для загрузки файло и кнопку для добавления подобных, связано со скриптом в
   * index.java
   *
   * @return html код в виде сущности (форма для загрузки(
   * @throws Exception
   */
  public AbsEnt multipleFileForm() throws Exception;

  public AbsEnt li(AbsEnt obj) throws Exception;

  public AbsEnt label(String forName, String value, String css, String javascript) throws Exception;

  public AbsEnt div(String style, Object value) throws Exception;

  /**
   * безопасно добавляет css
   */
  public AbsEnt addCss(AbsEnt ae, String css);

  /**
   * безопасно добавляет стиль
   */
  public AbsEnt addStyle(AbsEnt ae, String style);

  /**
   * регистрирует Exception, то есть записывает в renderResult полный стек объекта Exception
   *
   * @param exc объект Exception
   */
  public void registerException(Exception exc);

  public void addRenderResult(String str);

  public Object getReq(String name);

  public Object getSess(String name);

  public RightsObject getRights();

  public ActionResult getAr();

  public RenderConstant getRenderConstant();

  public AbstractApplication getApp();

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
  public AbsEnt multipleCombo(Map<String, Object> map, Object value, String name, Integer size) throws Exception;

  /**
   * возвращает select с множественным выбором
   *
   * @param service название сервиса
   * @param method название метода сервиса
   * @param value выбранное значение, либо массив выбранных значений, либо null
   * @param name параметр name в select
   * @param size параметр size. Может быть null
   * @return
   * @throws Exception
   */
  public AbsEnt multipleCombo(String service, String method, Object value, String name, Integer size) throws Exception;

  /**
   * Устанавливает базовое значение для ссылок в рендере к примеру для форм изменяя значение action
   * , для ссылок изменяет href до параметров
   */
  public Boolean setBaseLinkPath(String path);

  public AbsEnt p(String id, Object value, String css, String javaScript) throws Exception;

  @Deprecated
  public AbsEnt horizontalForm(Map<AbsEnt, String> inner, String title, String img, Boolean formToUploadFiles, String buttonName) throws Exception;

  @Deprecated
  public AbsEnt horizontalForm(Map<AbsEnt, String> inner, String title, String img) throws Exception;

  @Deprecated
  public AbsEnt verticalForm(Map<AbsEnt, String> inner, String title, String img, Boolean formToUploadFiles, String buttonName) throws Exception;

  @Deprecated
  public AbsEnt verticalForm(Map<AbsEnt, String> inner, String title, String img) throws Exception;

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img, Boolean validateRights, String ajax, Boolean formToUploadFiles, String buttonName) throws Exception;

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
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title) throws Exception;

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img, Boolean validateRights, String ajax, Boolean formToUploadFiles) throws Exception;

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img, Boolean validateRights, String ajax) throws Exception;

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img, Boolean validateRights) throws Exception;

  @Deprecated
  public AbsEnt rightForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img) throws Exception;

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
  @Deprecated
  public AbsEnt rightFormUploadFile(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img) throws Exception;

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
  public AbsEnt standartAjaxForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img) throws Exception;

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
  public AbsEnt standartAjaxForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String buttonName) throws Exception;

  @Deprecated
  public AbsEnt standartFileUploadForm(Boolean horizontal, String object, String action, String specAction, Map<AbsEnt, String> inner, String title, String img) throws Exception;

  
  public AbsEnt rightForm(Map<AbsEnt, String> inner, FormOptionInterface fo) throws Exception;

  public FormOptionInterface getFormOption();

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
   */
  @Deprecated
  public AbsEnt standartAjaxHref(String object, String action, String specAction, Map<String, Object> params, Object name) throws Exception;

  @Deprecated
  public AbsEnt href(String object, String action, String specAction, Map<String, Object> params, Object name, Boolean validateRights, String ajax, Boolean showTxtWithoutRights) throws Exception;

  @Deprecated
  public AbsEnt href(String object, String action, String SpecAction, Map<String, Object> params, Object name, Boolean validateRights) throws Exception;

  @Deprecated
  public AbsEnt href(String object, String action, String SpecAction, Map<String, Object> params, Object name) throws Exception;

  @Deprecated
  public AbsEnt href(String object, String action, String specAction, List<Parameter> params, Object name, Boolean validateRights, String ajax, Boolean showTxtWithoutRights) throws Exception;

  @Deprecated
  public AbsEnt href(String object, String action, String SpecAction, List<Parameter> params, Object name) throws Exception;

  public AbsEnt href(Map<String, Object> params, HrefOptionInterface ho) throws Exception;

  public AbsEnt href(List<Parameter> params, HrefOptionInterface ho) throws Exception;

  public HrefOptionInterface getHrefOption();

  public AbsEnt getImgByContent(String content, String width, String height, String style) throws Exception;

  /**
   * Возвращает объект, который представляет собой селект (раскрывающийся список). Если в полученном
   * списке нет значения по умолчанию, то значение по умолчанию добавляется в начало списка. Для
   * получения названия значения по умолчанию используется отдельный сервис.
   *
   * @param service название сервиса для получения данных
   * @param method название метода для получения данных
   * @param serviceForDefault название сервиса для получения имени значения по умолчанию
   * @param methodForDefault название метода для получения имени значения по умолчанию
   * @param value значение по умолчанию
   * @param name атрибут name
   * @return Объект, который представляет собой раскрывающийся список.
   * @throws Exception
   */
  public AbsEnt combo(String service, String method, String serviceForDefault, String methodForDefault, Object value, String name) throws Exception;

  public AbsEnt combo(String service, String method, String serviceForDefault, String methodForDefault, Object value, String name, boolean mandatory)
          throws Exception;
  
   /**
   * Возвращает объект, который представляет собой селект (раскрывающийся список). Если в полученном
   * списке нет значения по умолчанию, то значение по умолчанию добавляется в начало списка. Для
   * получения названия значения по умолчанию используется отдельный сервис.
   *
   * @param service название сервиса для получения данных
   * @param method название метода для получения данных
   * @param methodForDefault название метода для получения имени значения по умолчанию
   * @param value значение по умолчанию
   * @param name атрибут name
   * @return Объект, который представляет собой раскрывающийся список.
   * @throws Exception
   */
  public AbsEnt combo(String service, String method, String methodForDefault, Object value, String name) throws Exception;
}
