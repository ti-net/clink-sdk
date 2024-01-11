package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.PathEnum;
import com.tinet.clink.huanxin.model.IMRegisterUserModel;
import com.tinet.clink.huanxin.response.IMRegisterUserResponse;

import java.util.List;

public class IMRegisterUserRequest extends AbstractRequestModel<IMRegisterUserResponse> {

    private List<IMRegisterUserModel> registerUserModels;

    private String appKey;

    public List<IMRegisterUserModel> getRegisterUserModels() {
        return registerUserModels;
    }

    public void setRegisterUserModels(List<IMRegisterUserModel> registerUserModels) {
        this.registerUserModels = registerUserModels;
    }

    public IMRegisterUserRequest() {
        super(PathEnum.IM_REGISTER_USER.value(), HttpMethodType.POST);
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        putQueryParameter("appKey",appKey);
        this.appKey = appKey;
    }

    @Override
    public Class<IMRegisterUserResponse> getResponseClass() {
        return IMRegisterUserResponse.class;
    }


}
