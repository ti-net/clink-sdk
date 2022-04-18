package com.tinet.clink.openapi.request.dada;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.dada.GetCustomerStatusResponse;
import com.tinet.clink.openapi.response.dada.GetHotlineResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 登录请求
 *
 * @author lizy
 * @date 2018/10/24
 */
public class GetCustomerStatusRequest extends AbstractRequestModel<GetCustomerStatusResponse> {


    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public GetCustomerStatusRequest() {
        super(PathEnum.GetCustomerStatus.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetCustomerStatusResponse> getResponseClass() {
        return GetCustomerStatusResponse.class;
    }
}
