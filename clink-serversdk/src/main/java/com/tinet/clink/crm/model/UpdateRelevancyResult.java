package com.tinet.clink.crm.model;

/**
 * @author yangcr
 * @description: 更新客户资料关联的结果
 * @date 2024/6/21 14:06
 */
public class UpdateRelevancyResult {

    private Boolean value;

    private String msg;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

