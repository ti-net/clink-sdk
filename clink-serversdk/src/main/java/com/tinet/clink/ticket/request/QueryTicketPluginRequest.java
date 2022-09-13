package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.QueryTicketPluginResponse;

/**
 * @Description
 * @Author LinYang
 * @Date 2022/7/21
 * @Version 1.0
 **/
public class QueryTicketPluginRequest extends AbstractRequestModel<QueryTicketPluginResponse> {

    private Integer enterpriseId;
    private Integer id;
    private String extParam;

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

    public String getExtParam() {
        return extParam;
    }

    public void setExtParam(String extParam) {
        this.extParam = extParam;
        if (extParam != null) {
            putQueryParameter("extParam", extParam);
        }
    }

    public QueryTicketPluginRequest() {
        super(PathEnum.GetTicketPlugin.value(), HttpMethodType.GET);
    }

    @Override
    public Class<QueryTicketPluginResponse> getResponseClass() {
        return QueryTicketPluginResponse.class;
    }


}
