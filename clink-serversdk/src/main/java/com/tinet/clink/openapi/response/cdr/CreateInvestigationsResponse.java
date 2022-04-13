package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrInvestigationsSaveModel;
import com.tinet.clink.openapi.response.PagedResponse;

/**
 * @author libin
 * @date 2022-04-06 11:01 上午
 */
public class CreateInvestigationsResponse extends PagedResponse {

    private CdrInvestigationsSaveModel data;

    public CdrInvestigationsSaveModel getData() {
        return data;
    }

    public void setData(CdrInvestigationsSaveModel data) {
        this.data = data;
    }
}
