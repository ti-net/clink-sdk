package com.tinet.clink.cc.request.control;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.control.ConsultUnthreewayResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 咨询三方接回请求
 *
 * @author wangll
 * @date 2023-09-22
 **/
public class ConsultUnthreewayRequest extends AbstractRequestModel<ConsultUnthreewayResponse> {

    /**
     * 座席工号，4-11 位数字
     */
    private String cno;

    public ConsultUnthreewayRequest() {
        super(PathEnum.ConsultUnthreeway.value(), HttpMethodType.POST);
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
    public Class<ConsultUnthreewayResponse> getResponseClass() {
        return ConsultUnthreewayResponse.class;
    }
}
