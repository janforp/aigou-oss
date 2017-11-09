package com.aigou.oss.model.vo;

import com.aigou.oss.model.GoCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/8/1.
 * 品牌管理 列表
 */
public class BrandListVo {

    private Integer id;
    // 所属栏目ID:"12,27"
    private String cateid;
    // 所属栏目ID:12,27分开
    private List<Integer> cateIds;
    //栏目名称
    private List<String>  cateNames;
    private List<GoCategory> idToName;
    // 显示隐藏
    private String status;
    private String name;
    private Integer order;
    private String thumb;
    private String url;

    public List<GoCategory> getIdToName() {
        return idToName;
    }

    public void setIdToName(List<GoCategory> idToName) {
        this.idToName = idToName;
    }

    public List<String> getCateNames() {
        return cateNames;
    }

    public void setCateNames(List<String> cateNames) {
        this.cateNames = cateNames;
    }

    public List<Integer> getCateIds() {
        return cateIds;
    }

    public void setCateIds(List<Integer> cateIds) {
        this.cateIds = cateIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
