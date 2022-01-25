package com.tinet.clink.openapi.response.config.tel.restrict;

import com.tinet.clink.openapi.model.TelRestrictDeleteModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-13 11:12 上午
 */
public class DeleteTelRestrictResponse extends ResponseModel {

    private TelRestrictDeleteModel restrictTel;

    public TelRestrictDeleteModel getRestrictTel() {
        return restrictTel;
    }

    public void setRestrictTel(TelRestrictDeleteModel restrictTel) {
        this.restrictTel = restrictTel;
    }
}
