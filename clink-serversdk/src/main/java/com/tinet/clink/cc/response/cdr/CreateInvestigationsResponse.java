package com.tinet.clink.cc.response.cdr;


import com.tinet.clink.cc.model.CreateInvestigationsSaveModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author libin
 * @date 2022-04-06 11:01 上午
 */
public class CreateInvestigationsResponse extends ResponseModel {

    private CreateInvestigationsSaveModel investigation;

    public CreateInvestigationsSaveModel getInvestigation() {
        return investigation;
    }

    public void setInvestigation(CreateInvestigationsSaveModel investigation) {
        this.investigation = investigation;
    }
}
