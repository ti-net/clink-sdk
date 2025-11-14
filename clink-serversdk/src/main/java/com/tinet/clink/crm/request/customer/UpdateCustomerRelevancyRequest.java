package com.tinet.clink.crm.request.customer;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.response.customer.UpdateCustomerRelevancyResponse;

/**
 * @author yangcr
 * @description: 更新客户资料关联的请求
 * @date 2024/6/21 14:04
 */
public class UpdateCustomerRelevancyRequest extends AbstractRequestModel<UpdateCustomerRelevancyResponse> {

    /**
     * 客户资料ID
     */
    private Integer id;

    /**
     * 访客ID
     */
    private String visitorId;

    /**
     * 操作类型: null和0 新增访客id关联 1:取消访客id的关联
     */
    private Integer operationType;


    public UpdateCustomerRelevancyRequest() {
        super(PathEnum.UpdateCustomerRelevancy.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateCustomerRelevancyResponse> getResponseClass() {
        return UpdateCustomerRelevancyResponse.class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }
}
