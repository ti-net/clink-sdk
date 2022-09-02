package com.tinet.clink.cc.request.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.UnconsultResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 咨询接回请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class UnconsultRequest extends AbstractRequestModel<UnconsultResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    public UnconsultRequest() {
        super(PathEnum.Unconsult.value(), HttpMethodType.POST);
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

    @Override
    public Class<UnconsultResponse> getResponseClass() {
        return UnconsultResponse.class;
    }
}
