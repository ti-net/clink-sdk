package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for:
 * 元数据模型
 * @author : Tinet-yinzk
 * @date: 2023/6/4 21:39
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDataModel {
    /**
     * key
     */
    private String key;
    /**
     * 显示名称
     */
    private String name;
    /**
     * 值
     */
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
