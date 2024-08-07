package com.tinet.clink.cc.response.restrict;

import com.tinet.clink.cc.model.TelRestrictDeleteModel;
import com.tinet.clink.core.response.ResponseModel;

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
