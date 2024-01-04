package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.response.CreateAgentResponse;

public class CreateAgentRequest extends AbstractRequestModel<CreateAgentResponse> {


    /**
     * 坐席的昵称
     */
    private String nicename;
    /**
     * 账号(邮箱)
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String trueName;
    /**
     * 坐席类型：All 全渠道座席,Message 在线座席，Ticket 工单座席，CallCenter 呼叫中心座席，， MVCombine 视频客服（VEC独立视频），默认为在线坐席
     */
    private String agentType;
    /**
     * 电话号码
     */
    private Integer mobilePhone;
    /**
     * 工号
     */
    private Integer agentNumber;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 接待人数
     */
    private Integer maxServiceSessionCount;

    public String getNicename() {
        return nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public Integer getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Integer mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(Integer agentNumber) {
        this.agentNumber = agentNumber;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMaxServiceSessionCount() {
        return maxServiceSessionCount;
    }

    public void setMaxServiceSessionCount(Integer maxServiceSessionCount) {
        this.maxServiceSessionCount = maxServiceSessionCount;
    }

    public CreateAgentRequest(String path, HttpMethodType httpMethod) {
        super(path, httpMethod);
    }

    @Override
    public Class<CreateAgentResponse> getResponseClass() {
        return CreateAgentResponse.class;
    }
}
