package com.tinet.clink.livechat.model;

/**
 * 数据推送变量
 */
public class ChatHttpPushVariableModel {

    private String name;

    private String value;

    /**
     * 变量类型：0 表达式，1 字符串
     */
    private Integer type;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
