package com.tinet.clink.openapi.response.config.tel.restrict;

import com.tinet.clink.openapi.model.TelRestrictCreateModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 新增黑白名单队列响应
 * @author libin
 * @date 2021-12-13 10:32 上午
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  CreateTelRestrictResponse extends ResponseModel {

    private TelRestrictCreateModel restrictTel;

    public TelRestrictCreateModel getRestrictTel() {
        return restrictTel;
    }

    public void setRestrictTel(TelRestrictCreateModel restrictTel) {
        this.restrictTel = restrictTel;
    }
}
