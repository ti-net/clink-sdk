package com.tinet.clink.cc.response.investigation;

import com.tinet.clink.cc.model.InvestigationResultModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 满意度调查响应
 *
 **/
public class ListInvestigationResponse extends PagedResponse {


    private List<InvestigationResultModel> investigations;

    public List<InvestigationResultModel> getInvestigations() {
        return investigations;
    }

    public void setInvestigations(List<InvestigationResultModel> investigations) {
        this.investigations = investigations;
    }
}
