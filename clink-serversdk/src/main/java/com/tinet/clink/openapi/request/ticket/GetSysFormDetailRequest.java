package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.GetSysFormDetailResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取系统表单详情
 *
 * @author lize
 * @date: 2022/4/1
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  GetSysFormDetailRequest extends AbstractRequestModel<GetSysFormDetailResponse> {

    public GetSysFormDetailRequest() {
        super(PathEnum.GetSysFormDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetSysFormDetailResponse> getResponseClass() {
        return GetSysFormDetailResponse.class;
    }
}