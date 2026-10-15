package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.GetLeaveMessageTemplateResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 只读留言模板
 */
public class GetLeaveMessageTemplateRequest extends AbstractStatRequest<GetLeaveMessageTemplateResponse> {

    public GetLeaveMessageTemplateRequest() {
        super(PathEnum.GetLeaveMessageTemplate.value(), HttpMethodType.POST);
    }

    @Override
    public Class<GetLeaveMessageTemplateResponse> getResponseClass() {
        return GetLeaveMessageTemplateResponse.class;
    }
}
