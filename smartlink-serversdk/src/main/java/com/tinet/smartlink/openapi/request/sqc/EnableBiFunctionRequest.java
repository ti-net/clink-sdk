package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.EnableBiFunctionResponse;

/**
 * @author 王大宝
 * @date 2019/4/30
 */
public class EnableBiFunctionRequest extends BaseRequest<EnableBiFunctionResponse> {


    /**
     * 企业Id
     */
    private String enterpriseId;

    private Integer status;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
        if (enterpriseId != null) {
            putQueryParameter("enterpriseId", enterpriseId);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        if (status != null) {
            putQueryParameter("status", status);
        }
    }

    public EnableBiFunctionRequest() {
        super("/sqc/enableBi", HttpMethodType.GET);
    }


    @Override
    public Class<EnableBiFunctionResponse> getResponseClass() {
        return EnableBiFunctionResponse.class;
    }
}
