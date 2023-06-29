package com.tinet.clink.cc.model;

import java.util.Date;

/**
 * 短信模板查询结果对象
 *
 * @Author liyj
 * @Date 2023-06-27 15:46
 **/
public class SmsTemplateModel {

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 模板id
     */
    private String copTemplateId;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 打回原因
     */
    private String comment;

    /**
     * 创建时间，在前端展示叫提交时间
     */
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCopTemplateId() {
        return copTemplateId;
    }

    public void setCopTemplateId(String copTemplateId) {
        this.copTemplateId = copTemplateId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SmsTemplateModel{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", copTemplateId='" + copTemplateId + '\'' +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
