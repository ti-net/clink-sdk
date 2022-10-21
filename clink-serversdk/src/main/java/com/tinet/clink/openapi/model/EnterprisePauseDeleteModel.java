package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2021-12-15 4:30 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  EnterprisePauseDeleteModel {

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
