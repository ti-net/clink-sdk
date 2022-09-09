package com.tinet.clink.cc.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatIvrListResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author liurf
 * @date 2021/7/20 15:55
 **/
public class StatIvrListRequest extends AbstractStatRequest<StatIvrListResponse> {

    @Override
    public Class<StatIvrListResponse> getResponseClass() {
        return StatIvrListResponse.class;
    }

    public StatIvrListRequest() {
        super(PathEnum.StatIvrList.value(), HttpMethodType.POST);
    }

}
