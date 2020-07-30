package com.tinet.clink.openapi.model;

import java.util.Map;

/**
 * 会话详情类
 */
public class ChatMessage {

    private Map join;

    public Map getJoin() {
        return join;
    }

    public void setJoin(Map join) {
        this.join = join;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getSenderType() {
        return senderType;
    }

    public void setSenderType(Integer senderType) {
        this.senderType = senderType;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSensitiveWord() {
        return sensitiveWord;
    }

    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }

    /**
     * 主会话唯一标识
     */
    private String mainUniqueId;


    /**
     * 会话唯一标识
     */
    private String uniqueId;

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 消息发送方，1：座席、2：访客、3：系统、4：机器人
     */
    private Integer senderType;

    /**
     * 消息类型：1：文字、2：图片、3：视频、、、
     */
    private Integer messageType;

    /**
     * 消息来源
     */
    private String sender;

    /**
     * 发送人名称
     */
    private String sendName;

    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席名称
     */
    private String clientName;

    /**
     * 访客id
     */
    private String visitorId;

    /**
     * 访客姓名
     */
    private String visitorName;

    /**
     * 文件的地址
     */
    private String url;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 文件的地址
     */
    private String fileKey;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 机器人客服id
     */
    private String robotId;

    /**
     * 机器人客服名称
     */
    private String robotName;

    /**
     * 消息发送状态: 1-正常, 2-包含敏感词, 3-已被撤回
     */
    private Integer sendStatus;

    /**
     * 包含敏感词内容
     */
    private String sensitiveWord;
}
