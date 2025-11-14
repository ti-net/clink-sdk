package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.UpdateCdrResponse;

/**
 * @Author: liurf
 * @Description: 修改cdr信息
 * @Date: 2021/9/22 18:22
*/
public class UpdateCdrRequest extends BaseRequest<UpdateCdrResponse> {


    /**
     * 记录唯一标识
     */
    private String uniqueId;

    /**
     *  业务优先级
     */
    private String businessPriority;
    /**
     * 用户ID
     */
    private String userId;

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

    public String getBusinessPriority() {
        return businessPriority;
    }

    public void setBusinessPriority(String businessPriority) {
        this.businessPriority = businessPriority;
        if (businessPriority != null) {
            putBodyParameter("businessPriority", businessPriority);
        }
    }

    public UpdateCdrRequest() {
        super("/sqc/cdr", HttpMethodType.PUT);
    }

    @Override
    public Class<UpdateCdrResponse> getResponseClass() {
        return UpdateCdrResponse.class;
    }
}
