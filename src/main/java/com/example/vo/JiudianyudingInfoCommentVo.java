package com.example.vo;

import com.example.entity.JiudianyudingInfoComment;
import java.util.List;

public class JiudianyudingInfoCommentVo extends JiudianyudingInfoComment {

    private String foreignName;

    private List<JiudianyudingInfoCommentVo> children;

    public List<JiudianyudingInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<JiudianyudingInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}