package com.tinet.clink.kb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 竹间标准问实体
 *
 * @author feizq
 * @date 2022/07/26
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandardQuestionModel {

    /**
     * 标准问ID
     */
    private Integer id;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 标准问标题
     */
    private String title;

    /**
     * 标准问状态
     */
    private Integer status;

    /**
     * 语料数量
     */
    private Integer corpusCount;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCorpusCount() {
        return corpusCount;
    }

    public void setCorpusCount(Integer corpusCount) {
        this.corpusCount = corpusCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
