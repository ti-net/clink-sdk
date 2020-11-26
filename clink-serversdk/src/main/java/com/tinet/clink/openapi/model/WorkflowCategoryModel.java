package com.tinet.clink.openapi.model;

/**
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class WorkflowCategoryModel {

    /**
     * 工作流类别的id
     */
    private Integer id;

    /**
     * 工作流类别名称
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}