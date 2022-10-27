package com.tinet.clink.livechat.model;

import java.io.File;
import java.util.Date;

/**
 * 访客发送消息model参数
 *
 * @author: wangpw
 * @date: 2021/5/11
 */
public class ChatMessageSyncModel {

    /**
     * 消息发送方，1：座席、2：访客、3：机器人、4：系统
     */
    private Integer senderType;

    /**
     * 发送人ID
     */
    private String senderId;

    /**
     * 发送人名称
     */
    private String senderName;

    /**
     * 消息类型
     */
    private Integer messageType;

    /**
     * 内容
     */
    private String content;

    /**
     * 文件的地址
     */
    private String fileKey;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件url
     */
    private String fileUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    private File file;

    public Integer getSenderType() {
        return senderType;
    }

    public void setSenderType(Integer senderType) {
        this.senderType = senderType;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
