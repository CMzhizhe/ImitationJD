package com.example.imitationjd.model;

import java.util.List;

public class BottomModel extends BaseModel{
    private String name;
    private String desc;
    private boolean isSelected;
    private List<ContentModel> contentModelList;

    public List<ContentModel> getContentModelList() {
        return contentModelList;
    }

    public void setContentModelList(List<ContentModel> contentModelList) {
        this.contentModelList = contentModelList;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
