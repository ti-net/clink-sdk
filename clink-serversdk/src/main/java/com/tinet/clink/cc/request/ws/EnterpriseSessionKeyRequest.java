package com.tinet.clink.cc.request.ws;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.ws.EnterpriseSessionKeyResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @Author: zhoujiang
 * @Date: 2023/10/19 18:24
 */
public class EnterpriseSessionKeyRequest extends AbstractRequestModel<EnterpriseSessionKeyResponse> {

    public EnterpriseSessionKeyRequest() {
        super(PathEnum.EnterpriseSessionKey.value(), HttpMethodType.GET);
    }

    @Override
    public Class<EnterpriseSessionKeyResponse> getResponseClass() {
        return EnterpriseSessionKeyResponse.class;
    }
}
