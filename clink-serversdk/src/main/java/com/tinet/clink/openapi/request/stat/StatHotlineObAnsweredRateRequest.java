package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatHotlineIbResponse;
import com.tinet.clink.openapi.response.stat.StatHotlineObAnsweredRateResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatHotlineObAnsweredRateRequest extends AbstractStatRequest<StatHotlineObAnsweredRateResponse> {

    @Override
    public Class<StatHotlineObAnsweredRateResponse> getResponseClass() {
        return StatHotlineObAnsweredRateResponse.class;
    }

    public StatHotlineObAnsweredRateRequest() {
        super(PathEnum.StatHotlineObAnsweredRate.value(), HttpMethodType.POST);
    }

}
