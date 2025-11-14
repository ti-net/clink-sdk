package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.SessionInfoResponse;

/**
 * 查询会话详情
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionInfoRequest extends AbstractStatRequest<SessionInfoResponse> {


    private String mainUniqueId;

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    public SessionInfoRequest() {
        super(PathEnum.RecordSession.value(), HttpMethodType.GET);
    }

    @Override
    public Class<SessionInfoResponse> getResponseClass() {
        return SessionInfoResponse.class;
    }
}
