package com.tinet.clink.cc.model;

/**
 * Class For:
 * 智能助手 - 标签
 *
 * @author Tinet-yinzk
 * @date 2024/3/19 15:16
 */
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantTag {
    /**
     * 标签code
     */
    private String code;
    /**
     * 标签名
     */
    private String name;
    /**
     * 标签值
     */
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
