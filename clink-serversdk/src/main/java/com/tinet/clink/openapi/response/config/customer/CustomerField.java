package com.tinet.clink.openapi.response.config.customer;

/**
 * 客户资料字段相关属性
 *
 * @author jiangyang
 * @date 2019/11/13
 */
public class CustomerField {

    /**
     * 字段在天润系统中的不变属性，即id
     */
    private Integer key;

    /**
     * 字段中文描述
     */
    private String name;

    /**
     * 字段值
     */
    private String value;

    /**
     * 是否为天润系统固定字段 1：系统固定字段  0：客户自定义字段
     */
    private Integer system;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
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

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "CustomerField{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", system='" + system + '\'' +
                '}';
    }
}
