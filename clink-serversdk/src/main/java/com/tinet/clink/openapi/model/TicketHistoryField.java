package com.tinet.clink.openapi.model;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class TicketHistoryField {

    private Integer id;

    private Integer type;

    private String name;

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