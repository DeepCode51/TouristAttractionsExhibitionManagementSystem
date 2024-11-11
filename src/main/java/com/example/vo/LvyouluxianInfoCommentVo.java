package com.example.vo;

import com.example.entity.LvyouluxianInfoComment;
import java.util.List;

public class LvyouluxianInfoCommentVo extends LvyouluxianInfoComment {

    private String foreignName;

    private List<LvyouluxianInfoCommentVo> children;

    public List<LvyouluxianInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<LvyouluxianInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}