package com.tinet.clink.aikb.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author houwd
 * @since 2024/06/25
 */
public class MessageModel {

    /**
     * 1: 坐席侧  2：客户侧
     */
    private Integer side;

    /**
     * 对话文本
     */
    private String text;

    /**
     * 开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime startTime;

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
