package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.ChatMessageSyncModel;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatVisitorOpenSessionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;
import java.util.Map;

/**
 * 访客开始会话
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatVisitorOpenSessionRequest extends AbstractRequestModel<ChatVisitorOpenSessionResponse> {

    public ChatVisitorOpenSessionRequest() {
        super(PathEnum.ChatVisitorOpenSession.value(), HttpMethodType.POST);
    }

    /**
     * 渠道id
     */
    private String appId;

    /**
     * 访客id
     */
    private String visitorId;

    /**
     * 访客名称
     */
    private String visitorName;

    /**
     * 访客头像
     */
    private String avatar;

    /**
     * 客户资料信息
     */
    private Map<String, Object> customerFields;

    private Map<String, Object> extraInfo;

    private Map<String, Object> visitorExtraInfo;

    /**
     * 座席编号
     */
    private String cno;

    /**
     * 队列编号
     */
    private String qno;

    /**
     * 是否只做消息同步
     */
    private Boolean messageSyncOnly;

    /**
     * 同步的消息
     */
    private List<ChatMessageSyncModel> messages;

    /**
     * 会话创建时间
     */
    private Long createTime;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
        putQueryParameter("visitorId", visitorId);
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
        putQueryParameter("visitorName", visitorName);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        putQueryParameter("avatar", avatar);
    }

    public void setCustomerFields(Map<String, Object> customerFields) {
        this.customerFields = customerFields;
        putQueryParameter("customerFields", customerFields);
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
        putQueryParameter("extraInfo", extraInfo);
    }

    public void setVisitorExtraInfo(Map<String, Object> visitorExtraInfo) {
        this.visitorExtraInfo = visitorExtraInfo;
        putQueryParameter("visitorExtraInfo", visitorExtraInfo);
    }

    public void setCno(String cno) {
        this.cno = cno;
        putQueryParameter("cno", cno);
    }

    public void setQno(String qno) {
        this.qno = qno;
        putQueryParameter("qno", qno);
    }

    public void setMessageSyncOnly(Boolean messageSyncOnly) {
        this.messageSyncOnly = messageSyncOnly;
        putQueryParameter("messageSyncOnly", messageSyncOnly);
    }

    public void setMessages(List<ChatMessageSyncModel> messages) {
        this.messages = messages;
        putQueryParameter("messages", messages);
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
        putQueryParameter("createTime", createTime);
    }

    public String getAppId() {
        return appId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public String getAvatar() {
        return avatar;
    }

    public Map<String, Object> getCustomerFields() {
        return customerFields;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public Map<String, Object> getVisitorExtraInfo() {
        return visitorExtraInfo;
    }

    public String getCno() {
        return cno;
    }

    public String getQno() {
        return qno;
    }

    public Boolean getMessageSyncOnly() {
        return messageSyncOnly;
    }

    public List<ChatMessageSyncModel> getMessages() {
        return messages;
    }

    public Long getCreateTime() {
        return createTime;
    }

    @Override
    public Class<ChatVisitorOpenSessionResponse> getResponseClass() {
        return ChatVisitorOpenSessionResponse.class;
    }

    @Override
    public String toString() {
        return "ChatVisitorOpenSessionRequest{" +
                "appId='" + appId + '\'' +
                ", visitorId='" + visitorId + '\'' +
                ", visitorName='" + visitorName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", customerFields=" + customerFields +
                ", extraInfo=" + extraInfo +
                ", visitorExtraInfo=" + visitorExtraInfo +
                ", cno='" + cno + '\'' +
                ", qno='" + qno + '\'' +
                ", messageSyncOnly=" + messageSyncOnly +
                ", messages=" + messages +
                ", createTime=" + createTime +
                '}';
    }
}
