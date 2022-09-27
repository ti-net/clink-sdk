package com.tinet.clink.openapi.request.config.enterprise.pause;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.enterprise.pause.ListEnterprisePausesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询置忙状态
 *
 * @author wangll
 * @date 2021-07-06
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ListEnterprisePausesRequest extends AbstractRequestModel<ListEnterprisePausesResponse> {


    public ListEnterprisePausesRequest() {
        super(PathEnum.ListEnterprisePauses.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListEnterprisePausesResponse> getResponseClass() {
        return ListEnterprisePausesResponse.class;
    }


}
