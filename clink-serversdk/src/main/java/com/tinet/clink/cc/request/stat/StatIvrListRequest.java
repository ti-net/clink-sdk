package com.tinet.clink.cc.request.stat;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.stat.StatIvrListResponse;
import com.tinet.clink.core.utils.HttpMethodType;


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
