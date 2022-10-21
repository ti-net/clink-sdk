package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatCallIbAreaResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatCallIbAreaRequest extends AbstractStatRequest<StatCallIbAreaResponse> {

    @Override
    public Class<StatCallIbAreaResponse> getResponseClass() {
        return StatCallIbAreaResponse.class;
    }

    public StatCallIbAreaRequest() {
        super(PathEnum.StatCallIbArea.value(), HttpMethodType.POST);
    }

}
