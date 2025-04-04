package com.tinet.clink.ticket.model.childForm;


import java.util.List;

/**
 * @author lisong
 * @Description:
 * @date 2020/7/8 15:45
 */
public class OpenapiChildrenFormModel {
    /**
     * 详细字段
     */
    private List<OpenapiFormFieldModel> fields;

    /**
     * 分类名称
     */
    private String group;

    /**
     * 分类描述
     */
    private String groupDesc;

    /**
     * 分组序号
     */
    private Integer groupSerialNumber;

    public List<OpenapiFormFieldModel> getFields() {
        return fields;
    }

    public void setFields(List<OpenapiFormFieldModel> fields) {
        this.fields = fields;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Integer getGroupSerialNumber() {
        return groupSerialNumber;
    }

    public void setGroupSerialNumber(Integer groupSerialNumber) {
        this.groupSerialNumber = groupSerialNumber;
    }
}
