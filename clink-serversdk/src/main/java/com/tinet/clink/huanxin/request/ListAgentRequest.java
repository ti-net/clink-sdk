package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.response.ListAgentResponse;

public class ListAgentRequest extends AbstractRequestModel<ListAgentResponse> {

    private Integer page;

    private Integer size;

    /**
     * 坐席类型：All 全渠道座席,Message 在线座席，Ticket 工单座席，CallCenter 呼叫中心座席，， MVCombine 视频客服（VEC独立视频），默认全部
     */
    private String agentType;

    /**
     * 模糊查询，username（邮箱）,trueName（真实姓名）,agentType(坐席类型，默认全部),mobilePhone（手机号码）,agentNumber（工号）
     */
    private String keyValue;

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

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public ListAgentRequest(String path, HttpMethodType httpMethod) {
        super(path, httpMethod);
    }

    @Override
    public Class<ListAgentResponse> getResponseClass() {
        return ListAgentResponse.class;
    }
}
