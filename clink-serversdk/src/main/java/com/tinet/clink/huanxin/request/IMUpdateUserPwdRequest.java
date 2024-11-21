package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.PathEnum;
import com.tinet.clink.huanxin.model.IMRegisterUserModel;
import com.tinet.clink.huanxin.response.IMRegisterUserResponse;
import com.tinet.clink.huanxin.response.IMUserUpdatePwdResponse;

import java.util.List;

/**
 * IM 更新用户
 *
 * @author tian.jie
 * @date 2024-01-18 11:33
 */
public class IMUpdateUserPwdRequest extends AbstractRequestModel<IMUserUpdatePwdResponse> {


    /**
     * 新密码
     */
    private String newpassword;

    /**
     * 用户名
     */
    private String username;



    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        putQueryParameter("username",username);
    }

    public IMUpdateUserPwdRequest() {
        super(PathEnum.IM_UPDATE_PASSWORD.value(), HttpMethodType.PUT);
    }

    @Override
    public Class<IMUserUpdatePwdResponse> getResponseClass() {
        return IMUserUpdatePwdResponse.class;
    }


}
