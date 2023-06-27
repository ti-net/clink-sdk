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
     * 模板id
     */
    private Integer id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 内容
     */
    private String content;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 打回原因
     */
    private String comment;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Integer getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SmsTemplateModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
