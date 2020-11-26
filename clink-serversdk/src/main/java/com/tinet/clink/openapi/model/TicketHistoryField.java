package com.tinet.clink.openapi.model;

import java.util.List;

/**
 * 工单 表单中的字段对象
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class TicketHistoryField {

    /**
     * 字段Id
     */
    private Integer id;

    /**
     * 字段类型 1: 单行文本，2: 数值， 3: 邮件，4: IP地址，
     * <p>
     * 5: 多行文本，6: 下拉框，7: 自定义级联，
     * <p>
     * 8: 地区级联，9: 单选框，10: 复选框，11: 日期和时间，
     * <p>
     * 12: 日期，13: 时间，14: 文件
     */
    private Integer type;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段值
     */
    private String value;

    private List<TicketHistoryField> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public List<TicketHistoryField> getChildren() {
        return children;
    }

    public void setChildren(List<TicketHistoryField> children) {
        this.children = children;
    }
}