package com.tinet.clink.openapi.response.config.tel.restrict;

import com.tinet.clink.openapi.model.TelRestrictDeleteModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-13 11:12 上午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteTelRestrictResponse extends ResponseModel {

    private TelRestrictDeleteModel restrictTel;

    public TelRestrictDeleteModel getRestrictTel() {
        return restrictTel;
    }

    public void setRestrictTel(TelRestrictDeleteModel restrictTel) {
        this.restrictTel = restrictTel;
    }
}
