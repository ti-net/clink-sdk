package com.tinet.clink.openapi.response.config.enterprise.pause;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.openapi.model.EnterprisePauseUpdateModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-14 5:39 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  UpdateEnterprisePausesResponse extends ResponseModel {

    private EnterprisePauseUpdateModel enterprisePause;

    public EnterprisePauseUpdateModel getEnterprisePause() {
        return enterprisePause;
    }

    public void setEnterprisePause(EnterprisePauseUpdateModel enterprisePause) {
        this.enterprisePause = enterprisePause;
    }
}
