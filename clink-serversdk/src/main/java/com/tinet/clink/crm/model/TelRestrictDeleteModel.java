package com.tinet.clink.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author libin
 * @date 2021-12-15 4:14 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelRestrictDeleteModel {
    /**
     * 呼叫限制类型 1:黑名单 2:白名单
     */
    private Integer restrictType;

    /**
     * 电话号码
     */
    private String tel;

    public Integer getRestrictType() {
        return restrictType;
    }

    public void setRestrictType(Integer restrictType) {
        this.restrictType = restrictType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
