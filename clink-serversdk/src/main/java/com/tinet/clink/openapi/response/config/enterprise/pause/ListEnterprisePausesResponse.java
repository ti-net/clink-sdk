package com.tinet.clink.openapi.response.config.enterprise.pause;

import com.tinet.clink.openapi.model.EnterprisePauseModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author wangll
 */
public class ListEnterprisePausesResponse extends ResponseModel {

    private List<EnterprisePauseModel> enterprisePauses;

    public List<EnterprisePauseModel> getEnterprisePauses() {
        return enterprisePauses;
    }

    public void setEnterprisePauses(List<EnterprisePauseModel> enterprisePauses) {
        this.enterprisePauses = enterprisePauses;
    }
}
