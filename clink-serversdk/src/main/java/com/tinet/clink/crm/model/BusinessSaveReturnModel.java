package com.tinet.clink.crm.model;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Date;

/**
 * @author gexd
 * @date 2023/03/07
 */
public class BusinessSaveReturnModel {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 业务主题
     */
    private String topic;

    /**
     * 创建人类型。 2：openApi
     */
    private Integer creatorType;

    /**
     * 创建人座席。 -1：openApi
     */
    private String creatorCno;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人座席
     */
    private String modifierCno;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改人类型
     */
    private Integer modifierType;

    /**
     * 来源。24：坐席助手，30：接口创建
     */
    private Integer source;

    /**
     * 自定义字段
     */
    private ArrayNode customize;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录修改时间
     */
    private Date updateTime;

    /**
     * 在线会话ID
     */
    private String chatId;

    /**
     * 会话添加开始时间(用于确定会话数据的索引)
     */
    private Long chatStartTime;

    /**
     * 通话记录id
     */
    private String callId;

    /**
     * 从通话记录id
     */
    private String callUniqueId;

    /**
     * 在线从会话ID
     */
    private String chatUniqueId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(Integer creatorType) {
        this.creatorType = creatorType;
    }

    public String getCreatorCno() {
        return creatorCno;
    }

    public void setCreatorCno(String creatorCno) {
        this.creatorCno = creatorCno;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifierCno() {
        return modifierCno;
    }

    public void setModifierCno(String modifierCno) {
        this.modifierCno = modifierCno;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Integer getModifierType() {
        return modifierType;
    }

    public void setModifierType(Integer modifierType) {
        this.modifierType = modifierType;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }


    public ArrayNode getCustomize() {
        return customize;
    }

    public void setCustomize(ArrayNode customize) {
        this.customize = customize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Long getChatStartTime() {
        return chatStartTime;
    }

    public void setChatStartTime(Long chatStartTime) {
        this.chatStartTime = chatStartTime;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallUniqueId() {
        return callUniqueId;
    }

    public void setCallUniqueId(String callUniqueId) {
        this.callUniqueId = callUniqueId;
    }

    public String getChatUniqueId() {
        return chatUniqueId;
    }

    public void setChatUniqueId(String chatUniqueId) {
        this.chatUniqueId = chatUniqueId;
    }
}
