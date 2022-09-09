package com.tinet.clink.ticket.model;

import java.util.List;

public class AsrModel {

    /**
     * 企业id
     */
    private Integer enterpriseId;
    /**
     * 主话单和从话单的关连键
     */
    private String mainUniqueId;

    /**
     * 客户号码
     */
    private String customerNumber;

    /**
     * 座席工号
     */
    private String cno;
    /**
     * 开始时间(秒级时间戳)
     */
    private Long startTime;
    /**
     * 转写的结果数据
     */
    private List<Asr> agentAsrs;

    private List<Asr> clientAsrs;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public List<Asr> getAgentAsrs() {
        return agentAsrs;
    }

    public void setAgentAsrs(List<Asr> agentAsrs) {
        this.agentAsrs = agentAsrs;
    }

    public List<Asr> getClientAsrs() {
        return clientAsrs;
    }

    public void setClientAsrs(List<Asr> clientAsrs) {
        this.clientAsrs = clientAsrs;
    }

    public static class Asr {
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
         * 本句的平均语速，单位：每分钟字数
         */
        private Integer speechRate;
        /**
         * 本句与上一句之间的静音时长，单位：毫秒
         */
        private Integer silenceDuration;
        /**
         * 情绪能量值1-10，值越高情绪越强烈
         */
        private Integer emotionValue;
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

        public Integer getSpeechRate() {
            return speechRate;
        }

        public void setSpeechRate(Integer speechRate) {
            this.speechRate = speechRate;
        }

        public Integer getSilenceDuration() {
            return silenceDuration;
        }

        public void setSilenceDuration(Integer silenceDuration) {
            this.silenceDuration = silenceDuration;
        }

        public Integer getEmotionValue() {
            return emotionValue;
        }

        public void setEmotionValue(Integer emotionValue) {
            this.emotionValue = emotionValue;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }


}