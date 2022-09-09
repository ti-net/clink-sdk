package com.tinet.clink.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2021-12-15 4:30 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnterprisePauseDeleteModel {

    /**
     * 置忙状态描述
     */
    private String pauseStatus;

    public String getPauseStatus() {
        return pauseStatus;
    }

    public void setPauseStatus(String pauseStatus) {
        this.pauseStatus = pauseStatus;
    }
}
