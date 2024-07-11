package com.tinet.clink.ticket.model;

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

    /**
     * 评论人id
     */
    private String commentatorId;

    /**
     * 评论人id类型
     * 1:id 2:电话 3:员工号 4:用户名  默认为id
     */
    private Integer commentatorIdType = 1;

    /**
     * 自定义状态id
     */
    private String stateId;

    /**
     * 自定义状态id类型
     * 1:id 2:名称  默认为id
     */
    private Integer stateIdType = 1;

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

    public String getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(String commentatorId) {
        this.commentatorId = commentatorId;
    }

    public Integer getCommentatorIdType() {
        return commentatorIdType;
    }

    public void setCommentatorIdType(Integer commentatorIdType) {
        this.commentatorIdType = commentatorIdType;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Integer getStateIdType() {
        return stateIdType;
    }

    public void setStateIdType(Integer stateIdType) {
        this.stateIdType = stateIdType;
    }
}
