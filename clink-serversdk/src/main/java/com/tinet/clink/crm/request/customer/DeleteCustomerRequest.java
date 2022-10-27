package com.tinet.clink.crm.request.customer;

import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.response.customer.DeleteCustomerResponse;

/**
 * 删除客户资料请求
 *
 * @author hanjiale
 * @date 2020/11/30
 */
public class DeleteCustomerRequest extends AbstractRequestModel<DeleteCustomerResponse> {

    /**
     * 客户资料id
     */
    private Integer id;

    public DeleteCustomerRequest() {
        super(PathEnum.DeleteCustomer.value(), HttpMethodType.POST);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putBodyParameter("id", id);
        }
    }

    @Override
    public Class<DeleteCustomerResponse> getResponseClass() {
        return DeleteCustomerResponse.class;
    }
}
