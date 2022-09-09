package com.tinet.clink.cc.request.client;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.client.BindClientTelResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 绑定座席电话请求
 *
 * @author lizy
 * @date 2018/09/12
 */
public class BindClientTelRequest extends AbstractRequestModel<BindClientTelResponse> {

    /**
     * 座席号
     */
    private String cno;

    /**
     * 电话
     */
    private String tel;

    /**
     * 是否绑定
     */
    private Integer isBind;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
        if (tel != null) {
            putBodyParameter("tel", tel);
        }
    }

    public Integer getIsBind() {
        return isBind;
    }

    public void setIsBind(Integer isBind) {
        this.isBind = isBind;
        if (isBind != null) {
            putBodyParameter("isBind", isBind);
        }
    }

    public BindClientTelRequest() {
        super(PathEnum.BindClientTel.value(), HttpMethodType.POST);
    }

    @Override
    public Class<BindClientTelResponse> getResponseClass() {
        return BindClientTelResponse.class;
    }
}
