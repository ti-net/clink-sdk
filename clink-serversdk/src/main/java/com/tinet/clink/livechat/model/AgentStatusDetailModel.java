package com.tinet.clink.livechat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for:
 * 座席状态监控详情返回对象
 *
 * @author yinzk
 * @date 2022/2/9
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentStatusDetailModel {

    /**
     * 座席号
     */
    private String cno;
    /**
     * 座席名称
     */
    private String name;
    /**
     * 登录终端；1：工具条；2：座席；3：管理员；4：接口；6：APP,9:移动SDK
     */
    private Integer loginType;
    /**
     * 电话类型；1：电话；2：IP话机；3：软电话。
     */
    private Integer bindType;
    /**
     * 绑定号码
     */
    private String bindTel;
    /**
     * 座席状态; IDLE；空闲，PAUSE：置忙；WRAPUP：整理；CALLING：呼叫中；RINGING：响铃；BUSY：通话
     */
    private String status;
    /**
     * 座席状态详情，空闲，忙碌，整体，呼叫中，响铃，呼入振铃，外呼振铃，通话，呼入通话，外呼通话，自定义置忙状态
     */
    private String statusDetail;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }
}
