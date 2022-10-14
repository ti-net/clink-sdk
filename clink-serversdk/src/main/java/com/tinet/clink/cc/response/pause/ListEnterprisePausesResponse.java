package com.tinet.clink.cc.response.pause;

import com.tinet.clink.cc.model.EnterprisePauseModel;
import com.tinet.clink.core.response.ResponseModel;

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
