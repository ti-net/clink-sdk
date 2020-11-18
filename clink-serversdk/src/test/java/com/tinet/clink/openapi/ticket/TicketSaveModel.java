package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.NotNull;

/**
 * @author huwk
 * @date 2020/11/17
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketSaveModel {

    private String topic;

    private Integer level;

    private String[] state;

    private String callId;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String[] getState() {
        return state;
    }

    public void setState(String[] state) {
        this.state = state;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }
}
