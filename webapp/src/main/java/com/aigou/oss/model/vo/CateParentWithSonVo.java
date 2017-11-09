package com.aigou.oss.model.vo;

import com.aigou.oss.model.GoCategory;

import java.util.List;

/**
 * Created by Jan on 16/8/1.
 *
 * 栏目的父子关系
 */
public class CateParentWithSonVo {

    private GoCategory parent;
    private List<GoCategory> son;

    public GoCategory getParent() {
        return parent;
    }

    public void setParent(GoCategory parent) {
        this.parent = parent;
    }

    public List<GoCategory> getSon() {
        return son;
    }

    public void setSon(List<GoCategory> son) {
        this.son = son;
    }
}
