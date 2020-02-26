package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatQueueResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatQueueRequest extends AbstractStatRequest<StatQueueResponse> {

    @Override
    public Class<StatQueueResponse> getResponseClass() {
        return StatQueueResponse.class;
    }

    public StatQueueRequest() {
        super(PathEnum.StatQueue.value(), HttpMethodType.POST);
    }

}
