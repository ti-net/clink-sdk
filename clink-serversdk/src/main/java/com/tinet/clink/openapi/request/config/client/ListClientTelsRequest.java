package com.tinet.clink.openapi.request.config.client;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.client.ListClientTelsResponse;

/**
 * @author lizy
 * @date 2018/11/22
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListClientTelsRequest extends AbstractRequestModel<ListClientTelsResponse> {

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
