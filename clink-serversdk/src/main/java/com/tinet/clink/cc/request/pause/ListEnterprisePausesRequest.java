package com.tinet.clink.cc.request.pause;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.pause.ListEnterprisePausesResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


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
