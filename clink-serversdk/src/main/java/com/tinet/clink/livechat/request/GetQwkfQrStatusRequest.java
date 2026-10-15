package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.GetQwkfQrStatusResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 查询企微登录二维码状态
 */
public class GetQwkfQrStatusRequest extends AbstractStatRequest<GetQwkfQrStatusResponse> {

    /**
     * 二维码追踪ID
     */
    private String traceId;

    public GetQwkfQrStatusRequest() {
        super(PathEnum.GetQwkfQrStatus.value(), HttpMethodType.POST);
    }

    @Override
    public Class<GetQwkfQrStatusResponse> getResponseClass() {
        return GetQwkfQrStatusResponse.class;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
        if (traceId != null) {
            putQueryParameter("traceId", traceId);
        }
    }

}
