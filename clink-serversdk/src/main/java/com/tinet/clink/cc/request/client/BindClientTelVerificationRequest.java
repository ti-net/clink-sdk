package com.tinet.clink.cc.request.client;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.client.BindClientTelVerificationResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 发起绑定电话验证请求
 *
 * @author lizy
 * @date 2018/10/25
 */
public class BindClientTelVerificationRequest extends AbstractRequestModel<BindClientTelVerificationResponse> {

    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席绑定电话
     */
    private String tel;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putQueryParameter("cno", cno);
        }
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
        if (tel != null) {
            putQueryParameter("tel", tel);
        }
    }

    public BindClientTelVerificationRequest() {
        super(PathEnum.BindClientTelVerification.value(), HttpMethodType.GET);
    }

    @Override
    public Class<BindClientTelVerificationResponse> getResponseClass() {
        return BindClientTelVerificationResponse.class;
    }
}
