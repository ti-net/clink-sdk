package com.tinet.clink.cc.request.client;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.client.ListClientTelsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;


/**
 * @author lizy
 * @date 2018/11/22
 */
public class ListClientTelsRequest extends AbstractRequestModel<ListClientTelsResponse> {

    private String cno;

    private Integer telType;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putQueryParameter("cno", cno);
        }
    }

    public Integer getTelType() {
        return telType;
    }

    public void setTelType(Integer telType) {
        this.telType = telType;
        if (telType != null) {
            putQueryParameter("telType", telType);
        }
    }

    public ListClientTelsRequest() {
        super(PathEnum.ListClientTels.value());
    }

    @Override
    public Class<ListClientTelsResponse> getResponseClass() {
        return ListClientTelsResponse.class;
    }

}
