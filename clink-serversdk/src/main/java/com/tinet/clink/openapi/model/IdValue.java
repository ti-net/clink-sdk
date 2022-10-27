package com.tinet.clink.openapi.model;

/**
 * @author lizy
 * @date 2020/01/09
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  IdValue {

    /**
     * 字段id
     */
    private Integer id;

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
}
