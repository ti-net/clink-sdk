package com.tinet.clink.cc.model;

/**
 * 工单模板类别实体对象
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