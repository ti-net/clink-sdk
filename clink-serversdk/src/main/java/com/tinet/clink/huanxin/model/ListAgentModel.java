package com.tinet.clink.huanxin.model;

import java.util.List;

public class ListAgentModel {

    // 租户ID
    private int tenantId;
    // 坐席ID
    private String userId;
    // 坐席类型
    private String userType;
    // 所属范围
    private String userScope;
    // 密码
    private String password;
    // 账号(邮箱)
    private String username;
    // 角色
    private String roles;
    // 创建时间
    private String createDateTime;
    // 更新时间
    private String lastUpdateDateTime;
    // 启用状态
    private String status;
    // 在线状态
    private String state;
    // 接待人数
    private int maxServiceSessionCount;
    // 真实姓名
    private String trueName;
    // 电话号码
    private String mobilePhone;
    // 坐席类型
    private String agentType;
    // 角色列表
    private List<Role> roleList;
    // 头像
    private String avatar;
    // 所属范围
    private String scope;
    // 所属业务ID
    private String bizId;
    // 在线状态
    private String onLineState;
    // 当前在线状态
    private String currentOnLineState;

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserScope() {
        return userScope;
    }

    public void setUserScope(String userScope) {
        this.userScope = userScope;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(String lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getMaxServiceSessionCount() {
        return maxServiceSessionCount;
    }

    public void setMaxServiceSessionCount(int maxServiceSessionCount) {
        this.maxServiceSessionCount = maxServiceSessionCount;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getOnLineState() {
        return onLineState;
    }

    public void setOnLineState(String onLineState) {
        this.onLineState = onLineState;
    }

    public String getCurrentOnLineState() {
        return currentOnLineState;
    }

    public void setCurrentOnLineState(String currentOnLineState) {
        this.currentOnLineState = currentOnLineState;
    }

    public static class Role {
        // 角色ID
        private int role_id;
        // 角色名称
        private String role_name;

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }
    }
}
