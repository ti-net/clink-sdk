package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatMessageToClientResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 访客退出排队
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatMessageToClientRequest extends AbstractRequestModel<ChatMessageToClientResponse> {

    public ChatMessageToClientRequest() {
        super(PathEnum.ChatQuitQueue.value(), HttpMethodType.POST);
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
    public Class<ChatMessageToClientResponse> getResponseClass() {
        return ChatMessageToClientResponse.class;
    }

    @Override
    public String toString() {
        return "ChatMessageToClientRequest{" +
                "sessionId='" + sessionId + '\'' +
                ", messageType=" + messageType +
                ", content='" + content + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                "} " + super.toString();
    }
}
