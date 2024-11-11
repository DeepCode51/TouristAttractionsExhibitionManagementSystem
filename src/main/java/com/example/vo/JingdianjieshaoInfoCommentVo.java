package com.example.vo;

import com.example.entity.JingdianjieshaoInfoComment;
import java.util.List;

public class JingdianjieshaoInfoCommentVo extends JingdianjieshaoInfoComment {

    private String foreignName;

    private List<JingdianjieshaoInfoCommentVo> children;

    public List<JingdianjieshaoInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<JingdianjieshaoInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}