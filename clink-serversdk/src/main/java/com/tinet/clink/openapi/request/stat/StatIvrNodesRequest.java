package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatIvrNodesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author liurf
 * @date 2021/7/20 15:55
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  StatIvrNodesRequest extends AbstractStatRequest<StatIvrNodesResponse> {

    @Override
    public Class<StatIvrNodesResponse> getResponseClass() {
        return StatIvrNodesResponse.class;
    }

    public StatIvrNodesRequest() {
        super(PathEnum.StatIvrNodes.value(), HttpMethodType.POST);
    }

}
