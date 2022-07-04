package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.CdrSqcDetailsResponse;

/**
 * 获取通话质检详情
 * @author 王大宝
 * @date 2019/9/4
 */
public class CdrSqcDetailsRequest extends BaseRequest<CdrSqcDetailsResponse> {

    private String uniqueId;
    private String userId;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putQueryParameter("uniqueId", uniqueId);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putQueryParameter("userId", userId);
        }
    }

    public CdrSqcDetailsRequest() {
        super("/sqc/cdrSqcDetails", HttpMethodType.GET);
    }

    @Override
    public Class<CdrSqcDetailsResponse> getResponseClass() {
        return CdrSqcDetailsResponse.class;
    }
}
