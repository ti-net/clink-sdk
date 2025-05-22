package com.tinet.clink.huanxin.model;

public class IMUpdateUserPwdModel {

    private String action; //请求方式

    private Long timestamp; //请求时间

    private Integer duration; // 从发送 HTTP 请求到响应的时长, 单位为毫秒。

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
