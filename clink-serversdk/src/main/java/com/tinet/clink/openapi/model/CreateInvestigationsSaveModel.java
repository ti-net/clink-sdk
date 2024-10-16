package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2022-04-06 6:04 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  CreateInvestigationsSaveModel {

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
