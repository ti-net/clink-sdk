package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatClientStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatClientStatusRequest extends AbstractStatRequest<StatClientStatusResponse> {

    @Override
    public Class<StatClientStatusResponse> getResponseClass() {
        return StatClientStatusResponse.class;
    }

    public StatClientStatusRequest() {
        super(PathEnum.StatClientStatus.value(), HttpMethodType.POST);
    }

}
