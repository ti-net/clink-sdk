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
    /**
     *结果加密校验字段
     */
    private Boolean resultEncrypt;

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

    public Boolean getResultEncrypt() {
        return resultEncrypt;
    }

    public void setResultEncrypt(Boolean resultEncrypt) {
        this.resultEncrypt = resultEncrypt;
        if (resultEncrypt != null) {
            putQueryParameter("resultEncrypt", resultEncrypt);
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
