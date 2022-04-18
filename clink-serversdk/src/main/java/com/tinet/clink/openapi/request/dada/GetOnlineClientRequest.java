package com.tinet.clink.openapi.request.dada;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.dada.GetHotlineResponse;
import com.tinet.clink.openapi.response.dada.GetOnlineClientResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 登录请求
 *
 * @author lizy
 * @date 2018/10/24
 */
public class GetOnlineClientRequest extends AbstractRequestModel<GetOnlineClientResponse> {


    private String sno;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
        if (sno != null) {
            putBodyParameter("sno", sno);
        }
    }

    public GetOnlineClientRequest() {
        super(PathEnum.GetOnlineClient.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetOnlineClientResponse> getResponseClass() {
        return GetOnlineClientResponse.class;
    }
}
