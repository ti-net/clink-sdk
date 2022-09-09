package com.tinet.clink.livechat.model;

import java.util.Date;

/**
 * 工单评论对象
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class TicketCommentSearchResultModel {

    /**
     * 评论人/操作人类型 0：座席、1：管理员
     */
    private Integer operatorType;

    /**
     * 评论人/操作人
     */
    private String operator;

    /**
     * 评论人/操作人名称
     */
    private String operatorName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 附件相关信息
     */
    private String fileValue;

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileValue() {
        return fileValue;
    }

    public void setFileValue(String fileValue) {
        this.fileValue = fileValue;
    }
}