package com.tinet.clink.openapi.model;

/**
 * @author wangli
 * @date 2022-03-10 5:42 PM
 */
public class LoginLogModel {

    /**
     * 企业id
     */
    private String enterpriseId;

    /**
     * 企业编号
     */
    private String identifier;

    /**
     * 登录方式
     * 管理员：1 管理员后台 2管理员微信
     * 座席：3 座席前台 4座席微信 5工具条
     */
    private String loginMethod;

    /**
     * 客户编码，客户的唯一标识码
     */
    private String cno;

    /**
     * 所属队列号
     */
    private String qno;

    /**
     * 所属队列名
     */
    private String qname;

    /**
     * 用户名，座席侧此字段传用户名（中文等），管理员后台此处传用户编码（是唯一的）
     */
    private String username;

    /**
     * 登录IP
     */
    private String ip;

    /**
     * 登录类型： 1登入 2登出
     */
    private String loginType;

    /**
     * 登入/登出时间
     */
    private long logOnOrOutTime;

    /**
     * 用户类型： 1管理员 2座席
     */
    private String userType;

    /**
     * 登录结果 1 成功 2 失败
     */
    private String result;

    /**
     * 登录失败描述
     */
    private String description;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public long getLogOnOrOutTime() {
        return logOnOrOutTime;
    }

    public void setLogOnOrOutTime(long logOnOrOutTime) {
        this.logOnOrOutTime = logOnOrOutTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
