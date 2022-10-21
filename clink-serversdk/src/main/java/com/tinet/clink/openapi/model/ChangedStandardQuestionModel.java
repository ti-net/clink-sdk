package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 变更标准问实体
 *
 * @author feizq
 * @date 2022/09/22
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangedStandardQuestionModel {

    /**
     * 标准问ID
     */
    private Integer id;

    /**
     * 标准问标题
     */
    private String title;

    /**
     * 标准问是否是删除；true：删除，false：未删除
     */
    private Boolean sqIsDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSqIsDelete() {
        return sqIsDelete;
    }

    public void setSqIsDelete(Boolean sqIsDelete) {
        this.sqIsDelete = sqIsDelete;
    }
}
