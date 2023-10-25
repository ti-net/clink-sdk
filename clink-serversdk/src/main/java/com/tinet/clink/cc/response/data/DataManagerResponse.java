package com.tinet.clink.cc.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author xieyan
 * @date 2023/10/25 16:04
 * @description
 * @Version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataManagerResponse extends ResponseModel {

    /**
     * 键对应的值
     */
    private String value;

    /**
     * 有效期秒数
     */
    private Integer expired;

    /**
     * 可访问次数
     */
    private Integer accessLimit;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Integer getAccessLimit() {
        return accessLimit;
    }

    public void setAccessLimit(Integer accessLimit) {
        this.accessLimit = accessLimit;
    }
}
