package com.tinet.clink.aikb.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author houwd
 * @since 2024/06/25
 */
public class ChatSessionModel {

    /**
     * 会话/话单唯一标识
     */
    private String uniqueId;

    /**
     * 会话/话单开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 会话/话单结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 会话数据
     */
    private List<MessageModel> messages;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }
}
