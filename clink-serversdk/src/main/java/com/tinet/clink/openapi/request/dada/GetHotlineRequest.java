package com.tinet.clink.openapi.request.dada;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.dada.GetHotlineResponse;
import com.tinet.clink.openapi.response.dada.LoginResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 登录请求
 *
 * @author lizy
 * @date 2018/10/24
 */
public class GetHotlineRequest extends AbstractRequestModel<GetHotlineResponse> {


    private String sno;

    private String entryId;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
        if (sno != null) {
            putBodyParameter("sno", sno);
        }
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
        if (entryId != null) {
            putBodyParameter("entryId", entryId);
        }
    }

    public GetHotlineRequest() {
        super(PathEnum.GetHotline.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetHotlineResponse> getResponseClass() {
        return GetHotlineResponse.class;
    }
}
