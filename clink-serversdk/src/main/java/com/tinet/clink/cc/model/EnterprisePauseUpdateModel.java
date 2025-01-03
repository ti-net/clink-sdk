package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2021-12-15 4:22 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnterprisePauseUpdateModel {

    /**
     * id标识
     */
    private Integer id;

    /**
     * 置忙状态描述
     */
    private String pauseStatus;

    /**
     * 休息状态 0不是 1是
     */
    private Integer isRest;

    /**
     * 默认置忙状态 0不是 1是
     */
    private Integer isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPauseStatus() {
        return pauseStatus;
    }

    public void setPauseStatus(String pauseStatus) {
        this.pauseStatus = pauseStatus;
    }

    public Integer getIsRest() {
        return isRest;
    }

    public void setIsRest(Integer isRest) {
        this.isRest = isRest;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
