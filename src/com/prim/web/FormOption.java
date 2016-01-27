/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prim.web;

import com.prim.support.MyString;
import com.prim.web.fabric.AbsEnt;
import com.prim.web.objects.RenderTypes;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author кот
 */
class FormOption implements FormOptionInterface {

    private boolean horizontal = true;
    private String object = "";
    private String action = "";
    private String specAction = "";
    private String title = "";
    private String img = "";
    private Boolean file = false;
    private String buttonName;
    private Boolean validateRights = true;
    private Integer widthImg;
    private Integer heightImg;
    private RenderTypes renderType;
    private String js = "";
    private String buttonCssClass = "";
    private Map<AbsEnt, String> idMap = new HashMap();
    private ButtonPlace buttonPlace = ButtonPlace.END;

    private FormOption() {

    }

    public static FormOption getInstance() {
        FormOption fo = new FormOption();
        fo.setRenderType(RenderTypes.standart);
        fo.setValidateRights();
        fo.setHorisontal(Boolean.FALSE);
        fo.setFormToUploadFiles(Boolean.FALSE);
        return fo;
    }

    @Override
    public Map<AbsEnt, String> getIdMap() {
        return idMap;
    }

    @Override
    public void setIdMap(Map<AbsEnt, String> idMap) {
        this.idMap = idMap;
    }

    @Deprecated
    public Boolean isPlaceButtonAtBegin() {
        if (buttonPlace.equals(ButtonPlace.BEGIN) || buttonPlace.equals(ButtonPlace.ALL)) {
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    public void setPlaceButtonAtBegin(Boolean placeButtonAtBegin) {
        if (placeButtonAtBegin.equals(true)) {
            buttonPlace = ButtonPlace.BEGIN;
        } else {
            buttonPlace = ButtonPlace.END;
        }
    }

    @Override
    public void setHorisontal(Boolean horizontal) {
        if (horizontal != null) {
            this.horizontal = horizontal;
        }
    }

    public String getButtonCssClass() {
        return buttonCssClass;
    }

    public void setButtonCssClass(String buttonCssClass) {
        this.buttonCssClass = buttonCssClass;
    }

    public void addButtonCssClass(String buttonCssClass) {
        this.buttonCssClass += buttonCssClass;
    }

    @Override
    public void setObject(String object) {
        this.object = MyString.getString(object);
    }

    @Override
    public void setAction(String action) {
        this.action = MyString.getString(action);
    }

    @Override
    public void setSpecAction(String specAction) {
        this.specAction = MyString.getString(specAction);
    }

    @Override
    public void setTitle(String title) {
        this.title = MyString.getString(title);
    }

    @Override
    public void setImg(String img) {
        this.img = MyString.getString(img);
    }

    @Override
    public void setFormToUploadFiles(Boolean file) {
        if (file != null) {
            this.file = file;
        }
    }

    @Override
    public void setButtonName(String butName) {
        this.buttonName = MyString.getString(butName);
    }

    @Override
    public void setValidateRights() {
        this.validateRights = true;
    }

    @Override
    public void setNoValidateRights() {
        this.validateRights = false;
    }

    @Override
    public void setImgParams(Integer width, Integer height) {
        if (width != null) {
            this.widthImg = width;
        }
        if (height != null) {
            this.heightImg = height;
        }
    }

    @Override
    public void setRenderType(RenderTypes rt) {
        this.renderType = rt;
    }

    @Override
    public void setJsHandler(String jsOnclick) {
        this.js = MyString.getString(jsOnclick);
    }

    @Override
    public Boolean isHorizontal() {
        return horizontal;
    }

    @Override
    public String getObject() {
        return object;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public String getSpecAction() {
        return specAction;
    }

    @Override
    public String getTytle() {
        return title;
    }

    @Override
    public String getImg() {
        return img;
    }

    @Override
    public Boolean isFile() {
        return file;
    }

    @Override
    public String getВuttonName() {
        return buttonName;
    }

    @Override
    public Boolean isRights() {
        return validateRights;
    }

    @Override
    public Integer getImgWidth() {
        return widthImg;
    }

    @Override
    public Integer getImgHeight() {
        return heightImg;
    }

    @Override
    public RenderTypes getRenderType() {
        return renderType;
    }

    @Override
    public String getJshandler() {
        return js;
    }

    @Override
    public void setButtonPlase(ButtonPlace place) {
        buttonPlace = place;
    }

    @Override
    public ButtonPlace getButtonPlace() {
        return buttonPlace;
    }

    @Override
    public Boolean isPlaceButtonAtEnd() {
        if (buttonPlace.equals(ButtonPlace.END) || buttonPlace.equals(ButtonPlace.ALL)) {
            return true;
        } else {
            return false;
        }
    }

}
