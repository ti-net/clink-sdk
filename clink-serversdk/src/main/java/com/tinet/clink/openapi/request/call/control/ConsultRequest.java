package com.tinet.clink.openapi.request.call.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.ConsultResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 咨询请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class ConsultRequest extends AbstractRequestModel<ConsultResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 咨询类型，0:电话号码；1:座席号；2:分机号
     */
    private Integer consultType;

    /**
     * 咨询对象号码
     */
    private String consultNumber;

    public ConsultRequest() {
        super(PathEnum.Consult.value(), HttpMethodType.POST);
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

    public Integer getConsultType() {
        return consultType;
    }

    public void setConsultType(Integer consultType) {
        this.consultType = consultType;
        if (consultType != null) {
            putBodyParameter("consultType", consultType);
        }
    }

    public String getConsultNumber() {
        return consultNumber;
    }

    public void setConsultNumber(String consultNumber) {
        this.consultNumber = consultNumber;
        if (consultNumber != null) {
            putBodyParameter("consultNumber", consultNumber);
        }
    }

    @Override
    public Class<ConsultResponse> getResponseClass() {
        return ConsultResponse.class;
    }
}
