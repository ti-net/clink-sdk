package com.tinet.clink.cc.response.pause;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.openapi.model.EnterprisePauseCreateModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-14 5:28 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEnterprisePausesResponse extends ResponseModel {

    private EnterprisePauseCreateModel enterprisePause;

    public EnterprisePauseCreateModel getEnterprisePause() {
        return enterprisePause;
    }

    public void setEnterprisePause(EnterprisePauseCreateModel enterprisePause) {
        this.enterprisePause = enterprisePause;
    }
}
