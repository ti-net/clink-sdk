package com.tinet.smartlink.openapi.model.sqc;

/**
 * 平台类型
 *
 * @author 王大宝
 * @date 2020.03.12
 */
public enum ProductEnum {

    /**
     * 质检
     */
    SQC("sqc"),

    /**
     * 在线智能质检
     */
    IMSQC("imsqc"),

    /**
     * 在线人工质检
     */
    IMQC("imqc"),
    /**
     * 机器人
     */
    TBOT("tbot"),
    /**
     * 知识库
     */
    KB("kb");



    private String value;

    ProductEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
