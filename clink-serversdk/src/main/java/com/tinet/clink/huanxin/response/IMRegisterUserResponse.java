package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.huanxin.model.CreateAgentModel;
import com.tinet.clink.huanxin.model.IMRegisterUserRespModel;

public class IMRegisterUserResponse extends ResponseModel {

    private IMRegisterUserRespModel data;

    public IMRegisterUserRespModel getData() {
        return data;
    }

    public void setData(IMRegisterUserRespModel data) {
        this.data = data;
    }
}
