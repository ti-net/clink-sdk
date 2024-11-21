package com.tinet.clink.ticket.model;

public class SysFormField {
    /**
     * id
     */
    private Integer id;


    private String name;

    /**
     * 字段权限
     *
     * 注意区别
     * 插件页面字段权限（1:隐藏、2:只读、3:选填、4:必填）
     * 字段权限 1-必填， 2-选填，3-只读，4-隐藏
     */
    private Integer permission;

    /**
     * 外部客户权限（字段是否在自主工单详情展示），默认可见。0/null：不可见  1：可见
     * 这里只存了 自定义表单里面的字段配置
     * 属性字段的配置 在 fieldConfig (历史设计的原因 改不动了)
     *
     * #externalCustomerVisible
     */
    private Integer externalCustomerVisible;

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

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Integer getExternalCustomerVisible() {
        return externalCustomerVisible;
    }

    public void setExternalCustomerVisible(Integer externalCustomerVisible) {
        this.externalCustomerVisible = externalCustomerVisible;
    }
}
