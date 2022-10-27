package com.tinet.clink.kb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 语料返回对象
 *
 * @author feizq
 * @date 2022/07/26
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CorpusModel {

    /**
     * 语料ID
     */
    private Integer id;
    /**
     * 标准问ID
     */
    private Integer sqId;
    /**
     * 语料名称
     */
    private String corpusName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 状态；0：已训练，1：未训练
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
    }

    public String getCorpusName() {
        return corpusName;
    }

    public void setCorpusName(String corpusName) {
        this.corpusName = corpusName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
