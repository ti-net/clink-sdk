package com.tinet.clink.cc.response.cdr;

import com.tinet.clink.openapi.model.CdrInvestigationModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询满意度调查记录列表响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class ListInvestigationsResponse extends PagedResponse {

    /**
     * 满意度调查列表
     */
    private List<CdrInvestigationModel> cdrInvestigations;

    public List<CdrInvestigationModel> getCdrInvestigations() {
        return cdrInvestigations;
    }

    public void setCdrInvestigations(List<CdrInvestigationModel> cdrInvestigations) {
        this.cdrInvestigations = cdrInvestigations;
    }
}
