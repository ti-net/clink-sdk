package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.QueryTicketPluginByIdOrExternalParameterResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @Description
 * @Author LinYang
 * @Date 2022/7/21
 * @Version 1.0
 **/
public class QueryTicketPluginByIdOrExternalParameterRequest extends AbstractRequestModel<QueryTicketPluginByIdOrExternalParameterResponse> {

    private Integer enterpriseId;
    private Integer id;
    private String externalParameterSetting;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
        if (enterpriseId != null) {
            putQueryParameter("enterpriseId", enterpriseId);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public String getExternalParameterSetting() {
        return externalParameterSetting;
    }

    public void setExternalParameterSetting(String externalParameterSetting) {
        this.externalParameterSetting = externalParameterSetting;
        if (externalParameterSetting != null && !externalParameterSetting.equals("")) {
            putQueryParameter("externalParameterSetting", externalParameterSetting);
        }
    }

    public QueryTicketPluginByIdOrExternalParameterRequest() {
        super(PathEnum.GetTicketPluginByIdOrExternalParameter.value(), HttpMethodType.GET);
    }

    @Override
    public Class<QueryTicketPluginByIdOrExternalParameterResponse> getResponseClass() {
        return QueryTicketPluginByIdOrExternalParameterResponse.class;
    }


}
