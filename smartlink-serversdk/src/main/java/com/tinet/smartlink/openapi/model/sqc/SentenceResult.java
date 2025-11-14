package com.tinet.smartlink.openapi.model.sqc;

import java.io.Serializable;

/**
 * 转写的结果数据
 *
 * @author wenjd
 * @date 2019/04/03
 */
public class SentenceResult implements Serializable {

    /**
     * 该句所属的音轨ID
     */
    private Integer channelId;

    /**
     * 该句的起始时间偏移，单位：毫秒
     */
    private Integer beginTime;

    /**
     * 该句的结束时间偏移，单位：毫秒
     */
    private Integer endTime;

    /**
     * 情绪能量值1-10，值越高情绪越强烈
     */
    private Integer emotionValue;

    /**
     * 本句与上一句之间的静音时长，单位：毫秒
     */
    private Integer silenceDuration;

    /**
     * 本句的平均语速，单位：每分钟字数
     */
    private Integer speechRate;

    /**
     * 本句的转写文本结果
     */
    private String text;


    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getEmotionValue() {
        return emotionValue;
    }

    public void setEmotionValue(Integer emotionValue) {
        this.emotionValue = emotionValue;
    }

    public Integer getSilenceDuration() {
        return silenceDuration;
    }

    public void setSilenceDuration(Integer silenceDuration) {
        this.silenceDuration = silenceDuration;
    }

    public Integer getSpeechRate() {
        return speechRate;
    }

    public void setSpeechRate(Integer speechRate) {
        this.speechRate = speechRate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
