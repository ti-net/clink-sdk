package com.tinet.clink.livechat.model;

/**
 * 数据推送参数
 */
public class ChatHttpPushParameterModel {

    private String description;

    /**
     * 参数变量，不加 ${}
     */
    private String name;

    /**
     * 推送时参数名，加 ${}
     */
    private String value;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
