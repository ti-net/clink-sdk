package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.response.DeleteQueueResponse;

public class DeleteQueueRequest extends AbstractRequestModel<DeleteQueueResponse> {


    /**
     * 云呼：CallCenter，在线：Message
     */
    private String queueType;
    /**
     * 技能组id
     */
    private String queueId;
    /**
     * 坐席id（是指记录操作坐席）
     */
    private String userId;

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DeleteQueueRequest(String path, HttpMethodType httpMethod) {
        super(path, httpMethod);
    }

    @Override
    public Class<DeleteQueueResponse> getResponseClass() {
        return DeleteQueueResponse.class;
    }
}
