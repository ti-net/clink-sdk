package com.tinet.clink.openapi.model;

/**
 * @author lizy
 * @date 2019/08/08
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CallOutResultModel {


    /**
     * 座席号
     */
    private String cno;
    /**
     * 客户号码
     */
    private String customerNumber;


    /**
     * 请求的唯一id
     */
    private String requestUniqueId;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
    }
}
