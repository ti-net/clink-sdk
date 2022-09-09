package com.tinet.clink.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 分类返回对象
 *
 * @author feizq
 * @date 2022/06/20
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponseModel {
    /**
     * 分类ID
     */
    private Integer id;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 名称
     */
    private String name;
    /**
     * 父类ID
     */
    private Integer parentId;
    /**
     * 路径
     */
    private Integer[] path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer[] getPath() {
        return path;
    }

    public void setPath(Integer[] path) {
        this.path = path;
    }
}
