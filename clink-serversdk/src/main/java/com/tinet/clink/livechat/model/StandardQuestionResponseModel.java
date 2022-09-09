package com.tinet.clink.livechat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author feizq
 * @date 2022/06/15
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandardQuestionResponseModel{

    /**
     * 标准问ID
     */
    private Integer id;
    /**
     * 标准问状态
     */
    private Integer status;
    /**
     * 标准问问题
     */
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
