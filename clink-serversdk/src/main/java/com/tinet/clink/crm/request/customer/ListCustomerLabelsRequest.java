package com.tinet.clink.crm.request.customer;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.response.customer.ListCustomerLabelsResponse;

/**
 * 查询客户标签请求
 *
 * @author tian.jie
 * @date 2023-11-01 18:39
 */
public class ListCustomerLabelsRequest extends AbstractRequestModel<ListCustomerLabelsResponse> {

    /**
     * 客户姓名
     */
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        if (name != null) {
            putBodyParameter("name", name);
        }
    }

    public ListCustomerLabelsRequest() {
        super(PathEnum.QueryCustomerLabels.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListCustomerLabelsResponse> getResponseClass() {
        return ListCustomerLabelsResponse.class;
    }
}
