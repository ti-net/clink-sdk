package com.tinet.clink.cc.response.restrict;

import com.tinet.clink.cc.model.TelRestrictCreateModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * 新增黑白名单队列响应
 * @author libin
 * @date 2021-12-13 10:32 上午
 */
public class CreateTelRestrictResponse extends ResponseModel {

    private TelRestrictCreateModel restrictTel;

    public TelRestrictCreateModel getRestrictTel() {
        return restrictTel;
    }

    public void setRestrictTel(TelRestrictCreateModel restrictTel) {
        this.restrictTel = restrictTel;
    }
}
