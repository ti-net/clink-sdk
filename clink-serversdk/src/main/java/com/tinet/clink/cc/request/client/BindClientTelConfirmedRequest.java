package com.tinet.clink.cc.request.client;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.client.BindClientTelConfirmedResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 确认绑定电话验证请求
 *
 * @author lizy
 * @date 2018/10/25
 */
public class BindClientTelConfirmedRequest extends AbstractRequestModel<BindClientTelConfirmedResponse> {

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 座席号
     */
    private String cno;

    /**
     * 验证码
     */
    private String verificationCode;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
        if (tel != null) {
            putBodyParameter("tel", tel);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
        if (verificationCode != null) {
            putBodyParameter("verificationCode", verificationCode);
        }
    }

    public BindClientTelConfirmedRequest() {
        super(PathEnum.BindClientTelConfirmed.value(), HttpMethodType.POST);
    }

    @Override
    public Class<BindClientTelConfirmedResponse> getResponseClass() {
        return BindClientTelConfirmedResponse.class;
    }
}
