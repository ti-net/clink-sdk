package com.tinet.clink.cc.response.pause;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.cc.model.EnterprisePauseUpdateModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-14 5:39 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateEnterprisePausesResponse extends ResponseModel {

    private EnterprisePauseUpdateModel enterprisePause;

    public EnterprisePauseUpdateModel getEnterprisePause() {
        return enterprisePause;
    }

    public void setEnterprisePause(EnterprisePauseUpdateModel enterprisePause) {
        this.enterprisePause = enterprisePause;
    }
}
