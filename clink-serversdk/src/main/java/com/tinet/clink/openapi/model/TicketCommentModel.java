package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @description 工单评论实体
 * @Author DengJie
 * @Date 2022/04/21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketCommentModel {
    /**
     *工单 Id (id和externalId 二者必有一个)
     */
    private Integer id;

    /**
     * 工单 外部 Id (id和externalId 二者必有一个)
     */
    private String externalId;

    /**
     * 评论内容
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
