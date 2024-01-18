package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.PathEnum;
import com.tinet.clink.huanxin.model.IMRegisterUserModel;
import com.tinet.clink.huanxin.response.IMRegisterUserResponse;

import java.util.List;

/**
 * IM 注册用户请求
 *
 * @author tian.jie
 * @date 2024-01-18 11:33
 */
public class IMRegisterUserRequest extends AbstractRequestModel<IMRegisterUserResponse> {

    private List<IMRegisterUserModel> registerUserModels;


    public List<IMRegisterUserModel> getRegisterUserModels() {
        return registerUserModels;
    }

    public void setRegisterUserModels(List<IMRegisterUserModel> registerUserModels) {
        this.registerUserModels = registerUserModels;
    }

    public IMRegisterUserRequest() {
        super(PathEnum.IM_REGISTER_USER.value(), HttpMethodType.POST);
    }


    @Override
    public Class<IMRegisterUserResponse> getResponseClass() {
        return IMRegisterUserResponse.class;
    }


}
