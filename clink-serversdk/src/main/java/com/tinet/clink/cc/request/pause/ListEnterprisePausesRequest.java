package com.tinet.clink.cc.request.pause;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.enterprise.pause.ListEnterprisePausesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询置忙状态
 *
 * @author wangll
 * @date 2021-07-06
 */
public class ListEnterprisePausesRequest extends AbstractRequestModel<ListEnterprisePausesResponse> {


    public ListEnterprisePausesRequest() {
        super(PathEnum.ListEnterprisePauses.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListEnterprisePausesResponse> getResponseClass() {
        return ListEnterprisePausesResponse.class;
    }


}
