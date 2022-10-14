package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.QueryTicketPluginResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @Description
 * @Author LinYang
 * @Date 2022/7/21
 * @Version 1.0
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  QueryTicketPluginRequest extends AbstractRequestModel<QueryTicketPluginResponse> {

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
