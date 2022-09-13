package com.tinet.clink.cc.request.stat;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.stat.StatHotlineObAnsweredRateResponse;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatHotlineObAnsweredRateRequest extends AbstractStatRequest<StatHotlineObAnsweredRateResponse> {

    @Override
    public Class<StatHotlineObAnsweredRateResponse> getResponseClass() {
        return StatHotlineObAnsweredRateResponse.class;
    }

    public StatHotlineObAnsweredRateRequest() {
        super(PathEnum.StatHotlineObAnsweredRate.value(), HttpMethodType.POST);
    }

}
