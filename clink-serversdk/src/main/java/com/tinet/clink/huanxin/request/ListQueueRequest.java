package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.response.ListAgentResponse;
import com.tinet.clink.huanxin.response.ListQueueResponse;

public class ListQueueRequest extends AbstractRequestModel<ListQueueResponse> {

    private Integer page;

    private Integer size;

    /**
     * 云呼：CallCenter，在线：Messag
     */
    private String queueType;

    /**
     * 技能组名称，非必传
     */
    private String queueName;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public ListQueueRequest(String path, HttpMethodType httpMethod) {
        super(path, httpMethod);
    }

    @Override
    public Class<ListQueueResponse> getResponseClass() {
        return ListQueueResponse.class;
    }
}
