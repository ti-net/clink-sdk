package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatMessageToVisitorResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 发送消息到访客
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatMessageToVisitorRequest extends AbstractRequestModel<ChatMessageToVisitorResponse> {

    public ChatMessageToVisitorRequest() {
        super(PathEnum.ChatMessageToVisitor.value(), HttpMethodType.POST);
    }

    /**
     * 主会话标识
     */
    private String sessionId;

    /**
     * 消息类型
     */
    private Integer messageType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 文件类型消息文件访问地址
     */
    private String fileUrl;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        putQueryParameter("sessionId", sessionId);
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
        putQueryParameter("messageType", messageType);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        putQueryParameter("content", content);
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        putQueryParameter("fileUrl", fileUrl);
    }

    @Override
    public Class<ChatMessageToVisitorResponse> getResponseClass() {
        return ChatMessageToVisitorResponse.class;
    }

    @Override
    public String toString() {
        return "ChatMessageToVisitorRequest{" +
                "sessionId='" + sessionId + '\'' +
                ", messageType=" + messageType +
                ", content='" + content + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                "} " + super.toString();
    }
}
