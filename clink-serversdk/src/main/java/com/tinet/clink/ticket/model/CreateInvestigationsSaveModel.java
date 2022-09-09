package com.tinet.clink.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2022-04-06 6:04 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateInvestigationsSaveModel {

    /**
     * 唯一标识
     */
    private String mainUniqueId;

    /**
     * 满意度记录值
     */
    private Integer keys;

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public Integer getKeys() {
        return keys;
    }

    public void setKeys(Integer keys) {
        this.keys = keys;
    }
}
