package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.model.ChatHttpPushParameterModel;
import com.tinet.clink.livechat.model.ChatHttpPushVariableModel;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.CreateChatHttpPushResponse;

/**
 * 创建数据推送
 */
public class CreateChatHttpPushRequest extends AbstractStatRequest<CreateChatHttpPushResponse> {

    private String url;

    private Integer pushNotice;

    private ChatHttpPushVariableModel[] variable;

    private ChatHttpPushParameterModel[] parameter;

    private Integer timeout;

    private Integer retry;

    private Integer type;

    /**
     * 0 POST，1 GET
     */
    private Integer method;

    /**
     * 0 停用，1 启用；不传则停用
     */
    private Integer status;

    private String contentType;

    /**
     * 0 API 推送，1 智能体推送；不传按 API
     */
    private Integer pushTarget;

    private String agentId;

    public CreateChatHttpPushRequest() {
        super(PathEnum.CreateChatHttpPush.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateChatHttpPushResponse> getResponseClass() {
        return CreateChatHttpPushResponse.class;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPushNotice() {
        return pushNotice;
    }

    public void setPushNotice(Integer pushNotice) {
        this.pushNotice = pushNotice;
    }

    public ChatHttpPushVariableModel[] getVariable() {
        return variable;
    }

    public void setVariable(ChatHttpPushVariableModel[] variable) {
        this.variable = variable;
    }

    public ChatHttpPushParameterModel[] getParameter() {
        return parameter;
    }

    public void setParameter(ChatHttpPushParameterModel[] parameter) {
        this.parameter = parameter;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getPushTarget() {
        return pushTarget;
    }

    public void setPushTarget(Integer pushTarget) {
        this.pushTarget = pushTarget;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
