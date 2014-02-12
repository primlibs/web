package com.prim.web.webclient;

// класс отвечает за прием запроса от Web, сохранение параметров запроса
import com.prim.core.UploadedFile;
import com.prim.core.controller.RightsObject;
import com.prim.core.warehouse.OptionsKeeper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class StandartWebClientImpl implements WebClient {
  // объект Web-запроса
  private HttpServletRequest request;

  //переформированый запрос, проверенный на параметры, подается в приложение
  private HashMap<String, Object> innerRequest = new HashMap<String, Object>();

  private String renderType = WebClient.WEB;
  private String doAction = "default";
  private String activeObjects = "";
  private String activeAction = "";
  private Random random = new Random();
  private List<UploadedFile> fileList = new ArrayList();
  private HashMap<String, String> filearray = new HashMap<String, String>();
  private OptionsKeeper os;

  public StandartWebClientImpl(HttpServletRequest request, OptionsKeeper os) throws Exception {
    this.request = request;
    this.os = os;
    checkRequest();
  }

  @Override
  public HashMap<String, Object> getInnerRequest() {
    innerRequest.put("_FILEARRAY_", filearray);
    return innerRequest;
  }

  @Override
  public String getDoAction() {
    return doAction;
  }

  @Override
  public String getActiveObjects() {
    return activeObjects;
  }

  @Override
  public String getActiveAction() {
    return activeAction;
  }

  @Override
  public String getRenderType() {
    return renderType;
  }

  private void setRenderType(String renderType) {
    if (renderType != null && !"".equals(renderType)) {
      this.renderType = renderType;
    }

  }

  private void setDoAction(String doAction) {
    if (doAction != null && !"".equals(doAction)) {
      this.doAction = doAction;
    }
  }

  private void setActiveObjects(String activeObjects) {
    if (activeObjects != null) {
      this.activeObjects = activeObjects;
    }
  }

  private void setActiveAction(String activeAction) {
    if (activeAction != null) {
      this.activeAction = activeAction;
    }
  }

  public void setDefaultPair(RightsObject rights) {
    String[][] pairs = {{"clients", "showOne"},
      {"emailArea", "show"},
      {"serviceArea", "show"},
      {"bugTracker", "search"}};
    int object = 0;
    int action = 1;
    boolean pairFound = false;

    for (int curLine = 0; curLine < pairs.length && !pairFound; curLine++) {
      if (rights.methodInRight(pairs[curLine][object], pairs[curLine][action])) {
        setActiveObjects(pairs[curLine][object]);
        setActiveAction(pairs[curLine][action]);
        pairFound = true;
      }
    }
  }

  public void clearReq() {
    fileList.clear();
    filearray.clear();
    innerRequest.clear();
  }

  /**
   * проверяет запрос, выделяет Object-Name action также выделяет из запроса все
   * параметры
   */
  private void checkRequest() throws Exception {
    request.setCharacterEncoding("UTF-8");
    if (ServletFileUpload.isMultipartContent(request) == true) {
      checkMultypart();
    } else {
      for (String str : request.getParameterMap().keySet()) {
        checkParamsOnSpecName(str, request.getParameter(str));
      }
    }
  }

  /**
   * разбирает запрос вида Multipart
   *
   * @throws Exception
   */
  private void checkMultypart() throws Exception {
    DiskFileItemFactory factory = new DiskFileItemFactory();
    Integer maxSize;
    maxSize = 1024 * 1024 * os.getMaxUploadSizeMB();
    factory.setSizeThreshold(maxSize);
    File tempDir = new File(os.getUploadPath());
    factory.setRepository(tempDir);
    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setSizeMax(1024 * 1024 * os.getMaxUploadSizeMB() * 1000);
    upload.setHeaderEncoding(os.getDbEncoding());
    Boolean canUpload = true;

    List items = upload.parseRequest(request);
    Iterator iter = items.iterator();
    while (iter.hasNext()) {
      FileItem item = (FileItem) iter.next();
      item.getFieldName();
      if (item.isFormField()) {
        checkParamsOnSpecNameMultipart(item.getFieldName(), item.getString(os.getDbEncoding()));
      } else if (item.getName() != null && !item.getName().equals("") && canUpload == true) {
        File uploadedFile = null;
        do {
          String path = tempDir + "/" + random.nextInt(99999999) + item.getName();
          uploadedFile = new File(path);
        } while (uploadedFile.exists());
        //создаём файл
        uploadedFile.createNewFile();
        //записываем в него данные
        item.write(uploadedFile);
        if (uploadedFile.length() > maxSize) {
          uploadedFile.delete();
          canUpload = false;
        } else {
          filearray.put(uploadedFile.getAbsolutePath(), item.getName());
          fileList.add(new UploadedFile(uploadedFile.getAbsolutePath(),  item.getName()));
        }
      } else {
      }
    }

  }

  /**
   * Сохранение значения со стандартным именем
   *
   * @param name - наименование параметра
   * @param value - значение указанного выше параметра
   * @return стандартное имя, сохранено - true | нестандартное имя - false
   */
  private boolean saveValueWithStandardName(String name, Object value) {
    boolean valueIsSaved = true;
    if (name.equals(
            "object")) {
      setActiveObjects(value.toString().trim());
    } else if (name.equals(
            "action")) {
      setActiveAction(value.toString().trim());
      activeAction = value.toString().trim();
    } else if (name.equals(
            "renderType")) {
      setRenderType(value.toString().trim());
      renderType = value.toString().trim();
    } else if (name.equals(
            "specAction") && value != null && !value.toString().equals("")) {
      setDoAction(value.toString().trim());
      doAction = value.toString().trim();
    } else { //если не одно из условий не выполнилось
      valueIsSaved = false;
    }
    return valueIsSaved;
  }

  private void checkParamsOnSpecName(String name, Object value) {
    if (saveValueWithStandardName(name, value) == false) {
      String[] params = request.getParameterValues(name);
      if (params != null && params.length > 1) {
        innerRequest.put(name, params);
      } else {
        innerRequest.put(name, value.toString().trim());
      }
    }
  }

  private void checkParamsOnSpecNameMultipart(String name, Object value) {

    if (name == null || value == null) {
      return;
    }

    if (saveValueWithStandardName(name, value) == false) {
      if (!innerRequest.containsKey(name)) {
        innerRequest.put(name, value);
      } else {
        try {
          String[] str = (String[]) innerRequest.get(name);
          String[] newStr = Arrays.copyOf(str, str.length + 1);
          newStr[str.length] = value.toString();
          innerRequest.put(name, newStr);
        } catch (Exception e) {
          String oldValue = innerRequest.get(name).toString();
          String[] newStr2 = {oldValue, value.toString()};
          innerRequest.put(name, newStr2);
        }
      }
    }
  }

  @Override
  public List<UploadedFile> getUploadedFile() {
    return fileList;
  }
}
