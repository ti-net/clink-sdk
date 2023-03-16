package com.tinet.clink.crm.model;


/**
 * 自定义字段实体
 *
 * @author gexd
 * @date 2023/03/08
 */
public class CustomizeField {

    /**
     * 自定义字段id
     */
    private Integer id;
    /**
     * 自定义字段名称
     */
    private String name;
    /**
     * 自定义字段值
     */
    private String value;
    /**
     * 自定义字段类型
     */
    private Integer type;

    /**
     * 自定义字段加密值
     */
    private String encryptValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEncryptValue() {
        return encryptValue;
    }

    public void setEncryptValue(String encryptValue) {
        this.encryptValue = encryptValue;
    }
}
