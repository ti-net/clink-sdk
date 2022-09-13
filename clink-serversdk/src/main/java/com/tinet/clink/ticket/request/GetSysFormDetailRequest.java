package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.GetSysFormDetailResponse;

/**
 * 获取系统表单详情
 *
 * @author lize
 * @date: 2022/4/1
 **/
public class GetSysFormDetailRequest extends AbstractRequestModel<GetSysFormDetailResponse> {

    public GetSysFormDetailRequest() {
        super(PathEnum.GetSysFormDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetSysFormDetailResponse> getResponseClass() {
        return GetSysFormDetailResponse.class;
    }
}