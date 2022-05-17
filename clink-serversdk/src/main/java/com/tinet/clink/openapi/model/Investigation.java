package com.tinet.clink.openapi.model;

/**
 * 满意度调查返回对象
 *
 * @author huwk
 * @date 2018/09/11
 **/
public class Investigation {

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 座席号
     */
    private String cno;

    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 按键值
     */
    private Integer keys;

    /**
     * 按键
     */
    private String pressKey;

    /**
     * 按键时间
     */
    private Long pressTime;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getKeys() {
        return keys;
    }

    public void setKeys(Integer keys) {
        this.keys = keys;
    }


    public String getPressKey() {
        return pressKey;
    }

    public void setPressKey(String pressKey) {
        this.pressKey = pressKey;
    }

    public Long getPressTime() {
        return pressTime;
    }

    public void setPressTime(Long pressTime) {
        this.pressTime = pressTime;
    }
}
