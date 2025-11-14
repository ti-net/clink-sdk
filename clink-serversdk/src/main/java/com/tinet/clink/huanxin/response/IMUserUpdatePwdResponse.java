package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.huanxin.model.IMRegisterUserRespModel;
import com.tinet.clink.huanxin.model.IMUpdateUserPwdModel;

public class IMUserUpdatePwdResponse extends ResponseModel {

    private IMUpdateUserPwdModel data;

    public IMUpdateUserPwdModel getData() {
        return data;
    }

    public void setData(IMUpdateUserPwdModel data) {
        this.data = data;
    }
}
