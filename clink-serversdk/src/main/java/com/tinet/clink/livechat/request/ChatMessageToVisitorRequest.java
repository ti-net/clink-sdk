package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatMessageToVisitorResponse;

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
     * 座席工号
     */
    private String cno;

    /**
     * 发送人类型
     */
    private Integer senderType;

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

    /**
     * 文件类型消息文件名
     */
    private String fileName;


    public void setCno(String cno) {
        this.cno = cno;
        putQueryParameter("cno", cno);
    }

    public void setSenderType(Integer senderType) {
        this.senderType = senderType;
        putQueryParameter("senderType", senderType);
    }

    public String getCno() {
        return cno;
    }

    public Integer getSenderType() {
        return senderType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        putQueryParameter("fileName", fileName);
    }

    public String getFileName() {
        return fileName;
    }


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
                "cno='" + cno + '\'' +
                ", senderType=" + senderType +
                ", sessionId='" + sessionId + '\'' +
                ", messageType=" + messageType +
                ", content='" + content + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileName='" + fileName + '\'' +
                "} " + super.toString();
    }
}
