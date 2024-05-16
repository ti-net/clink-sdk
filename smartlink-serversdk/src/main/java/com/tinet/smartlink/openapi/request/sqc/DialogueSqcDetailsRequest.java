package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.DialogueSqcDetailsResponse;

/**
 * 获取通话质检详情
 *
 * @author 王大宝
 * @date 2019/9/4
 */
public class DialogueSqcDetailsRequest extends BaseRequest<DialogueSqcDetailsResponse> {

    private String id;

    private String uniqueId;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

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

    public DialogueSqcDetailsRequest() {
        super("/sqc/dialogueSqcDetails", HttpMethodType.GET);
    }

    @Override
    public Class<DialogueSqcDetailsResponse> getResponseClass() {
        return DialogueSqcDetailsResponse.class;
    }
}
