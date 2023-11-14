package com.tinet.clink.cc.request.investigation;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.investigation.ListInvestigationResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 满意度调查模板请求
 *
 **/
public class ListInvestigationRequest extends AbstractRequestModel<ListInvestigationResponse> {

    public ListInvestigationRequest() {
        super(PathEnum.ListInvestigation.value(), HttpMethodType.GET);
    }

    /**
     * 外显号码
     */
    private String[] obClid;

    /**
     * 语音导航
     */
    private String[] ivrName;


    private Integer limit;

    private Integer offset;


    public String[] getObClid() {
        return obClid;
    }

    public void setObClid(String[] obClid) {
        this.obClid = obClid;
        if (obClid != null) {
            putQueryParameter("obClid", obClid);
        }
    }

    public String[] getIvrName() {
        return ivrName;
    }

    public void setIvrName(String[] ivrName) {
        this.ivrName = ivrName;
        if (ivrName != null) {
            putQueryParameter("ivrName", ivrName);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }


    @Override
    public Class<ListInvestigationResponse> getResponseClass() {
        return ListInvestigationResponse.class;
    }
}
