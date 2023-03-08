package com.tinet.clink.crm.request;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.response.CreateBusinessResponse;

/**
 * 新增业务记录
 *
 * @author gexd
 * @date 2023/03/08
 */
public class CreateBusinessRequest extends AbstractRequestModel<CreateBusinessResponse> {

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
     * 来源
     */
    private Integer source;
    /**
     * 自定义字段
     */
    private ArrayNode customize;

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

    public CreateBusinessRequest() {
        super(PathEnum.CreateBusiness.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateBusinessResponse> getResponseClass() {
        return CreateBusinessResponse.class;
    }
}
