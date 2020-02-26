package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatClientWorkloadResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatClientWorkloadRequest extends AbstractStatRequest<StatClientWorkloadResponse> {

    @Override
    public Class<StatClientWorkloadResponse> getResponseClass() {
        return StatClientWorkloadResponse.class;
    }

    public StatClientWorkloadRequest() {
        super(PathEnum.StatClientWorkload.value(), HttpMethodType.POST);
    }

}
