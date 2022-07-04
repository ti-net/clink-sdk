package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushInvestigationResponse;

/**
 * 推送满意度调查信息
 *
 * @author wenjd
 * @date 2019/04/03
 */
public class PushInvestigationRequest extends BaseRequest<PushInvestigationResponse> {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 记录唯一标识
     */
    private String uniqueId;

    /**
     * 满意度评价
     */
    private String keys;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putBodyParameter("uniqueId", uniqueId);
        }
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
        if (keys != null) {
            putBodyParameter("keys", keys);
        }
    }

    public PushInvestigationRequest() {
        super("/sqc/investigation", HttpMethodType.POST);
    }


    @Override
    public Class<PushInvestigationResponse> getResponseClass() {
        return PushInvestigationResponse.class;
    }

}
