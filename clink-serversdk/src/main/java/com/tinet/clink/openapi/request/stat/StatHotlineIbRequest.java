package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatHotlineIbResponse;
import com.tinet.clink.openapi.response.stat.StatQueueResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatHotlineIbRequest extends AbstractStatRequest<StatHotlineIbResponse> {

    @Override
    public Class<StatHotlineIbResponse> getResponseClass() {
        return StatHotlineIbResponse.class;
    }

    public StatHotlineIbRequest() {
        super(PathEnum.StatHotlineIb.value(), HttpMethodType.POST);
    }

}
