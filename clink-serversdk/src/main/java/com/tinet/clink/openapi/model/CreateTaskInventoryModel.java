package com.tinet.clink.openapi.model;

/**
 * 外呼任务详情创建model
 *
 * @author: wangpw
 * @date: 2022/5/17
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CreateTaskInventoryModel {
    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户号码
     */
    private String customerTel;

    /**
     * 备注
     */
    private String remark;


    /**
     * 自定义字段(非系统字段)
     */
    private TaskInventoryCustomizeFieldModel[] customize;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public TaskInventoryCustomizeFieldModel[] getCustomize() {
        return customize;
    }

    public void setCustomize(TaskInventoryCustomizeFieldModel[] customize) {
        this.customize = customize;
    }

}
