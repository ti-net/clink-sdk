package com.tinet.clink.livechat.model;

/**
 * 外呼任务详情自定义字段model
 *
 * @author: wangpw
 * @date: 2022/5/17
 */
public class TaskInventoryCustomizeFieldModel {
    /**
     * 自定义字段名称
     */
    private String name;
    /**
     * 自定义字段值
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
