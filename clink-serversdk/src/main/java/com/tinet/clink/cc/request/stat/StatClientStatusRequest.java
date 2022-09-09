package com.tinet.clink.cc.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatClientStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatClientStatusRequest extends AbstractStatRequest<StatClientStatusResponse> {

    @Override
    public Class<StatClientStatusResponse> getResponseClass() {
        return StatClientStatusResponse.class;
    }

    public StatClientStatusRequest() {
        super(PathEnum.StatClientStatus.value(), HttpMethodType.POST);
    }

}
