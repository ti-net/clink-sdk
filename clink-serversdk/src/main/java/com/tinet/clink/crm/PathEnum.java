package com.tinet.clink.crm;

/**
 * @author wangll
 * @date 2019/2/19
 */
public enum PathEnum {
    //客户资料-获取可用查询参数
    CustomerParam("crm/customer_params"),
    //客户资料-查询客户资料列表
    ListCustomers("crm/list_customers"),
    //创建客户资料
    CreateCustomer("crm/create_customer"),

    //更新客户资料
    UpdateCustomer("crm/update_customer"),

    //更新客户资料
    UpdateCustomerByExternalId("crm/update_customer_by_external_id"),
    //创建客户资料
    ListCustomerField("crm/list_customer_field"),
    // 删除客户资料
    DeleteCustomer("crm/delete_customer"),


    //查询业务记录的自定义字段
    ListBusinessField("crm/list_business_customize_field"),

    //查询业务记录
    ListBusiness("crm/list_business"),

    //查询业务记录
    GetBusinessDetail("crm/get_business_detail");

    /**
     * 外呼任务创建
     */
    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
