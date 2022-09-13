package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatMessageToClientResponse;

import java.io.File;
import java.util.*;

/**
 * 发送消息到座席
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatMessageToClientRequest extends AbstractRequestModel<ChatMessageToClientResponse> {

    public ChatMessageToClientRequest() {
        super(PathEnum.ChatMessageToClient.value(), HttpMethodType.POST);
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

    /**
     * 文件类型消息文件名
     */
    private String fileName;

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        List<File> fileList = new ArrayList<>();
        fileList.add(file);
        Map<String, List<File>> fileMap = new HashMap<>();
        fileMap.put("file", fileList);
        setFileMap(fileMap);
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
                ", fileName='" + fileName + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean isMultipartFormData() {
        return Objects.nonNull(getFile());
    }
}
