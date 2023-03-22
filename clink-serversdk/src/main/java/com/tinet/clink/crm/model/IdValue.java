package com.tinet.clink.crm.model;

/**
 * @author lizy
 * @date 2020/01/09
 */
public class IdValue {

    /**
     * 字段id
     */
    private Integer id;

    /**
     * 字段name
     * @since 3.0.7
     */
    private String name;

    /**
     * 字段的值
     */
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
