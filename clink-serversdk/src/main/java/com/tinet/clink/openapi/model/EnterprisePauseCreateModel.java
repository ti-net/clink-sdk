package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户置忙状态model
 *
 * @author wangyq
 * @date 2018/07/10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  EnterprisePauseCreateModel {

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