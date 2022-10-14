package com.tinet.clink.openapi.response.config.customer;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 删除客户资料响应
 *
 * @author hanjiale
 * @date 2020/11/30
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteCustomerResponse extends ResponseModel {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
