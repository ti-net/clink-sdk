package com.tinet.clink.cc.request.stat;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.stat.StatCallIbAreaResponse;
import com.tinet.clink.core.utils.HttpMethodType;


public class StatCallIbAreaRequest extends AbstractStatRequest<StatCallIbAreaResponse> {

    @Override
    public Class<StatCallIbAreaResponse> getResponseClass() {
        return StatCallIbAreaResponse.class;
    }

    public StatCallIbAreaRequest() {
        super(PathEnum.StatCallIbArea.value(), HttpMethodType.POST);
    }

}
