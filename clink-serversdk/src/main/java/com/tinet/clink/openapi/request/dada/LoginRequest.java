package com.tinet.clink.openapi.request.dada;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.ClidArea;
import com.tinet.clink.openapi.model.ClientPermission;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.client.CreateClientResponse;
import com.tinet.clink.openapi.response.dada.LoginResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;

/**
 * 登录请求
 *
 * @author lizy
 * @date 2018/10/24
 */
public class LoginRequest extends AbstractRequestModel<LoginResponse> {


    /**
     * 登录类型 1 座席 2 管理员
     */
    private Integer loginType;

    /**
     * 达达C端客户id
     */
    private String userId;

    /**
     * 达达B端座席登录编号
     */
    private String loginAccount;

    /**
     * 达达B端座席登录用户名
     */
    private String loginName;


    /**
     * 登录类型 1 pc端登录 2 C端客户登录
     */
    private Integer type;

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
        if (loginType != null) {
            putBodyParameter("loginType", loginType);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
        if (loginAccount != null) {
            putBodyParameter("loginAccount", loginAccount);
        }
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
        if (loginName != null) {
            putBodyParameter("loginName", loginName);
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            putBodyParameter("type", type);
        }
    }

    public LoginRequest() {
        super(PathEnum.LoginToken.value(), HttpMethodType.POST);
    }

    @Override
    public Class<LoginResponse> getResponseClass() {
        return LoginResponse.class;
    }
}
