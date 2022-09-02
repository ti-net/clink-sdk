package com.tinet.clink.cc.request.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.ConsultThreewayResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 咨询三方请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class ConsultThreewayRequest extends AbstractRequestModel<ConsultThreewayResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 通话限制时间，单位：秒
     */
    private Integer limitTimeSecond;

    /**
     * 提前提醒时间，单位：秒
     */
    private Integer limitTimeAlertSecond;

    /**
     * 提醒语音文件名称
     */
    private String limitTimeFile;

    public ConsultThreewayRequest() {
        super(PathEnum.ConsultThreeway.value(), HttpMethodType.POST);
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

    public Integer getLimitTimeSecond() {
        return limitTimeSecond;
    }

    public void setLimitTimeSecond(Integer limitTimeSecond) {
        this.limitTimeSecond = limitTimeSecond;
        if (limitTimeSecond != null) {
            putBodyParameter("limitTimeSecond", limitTimeSecond);
        }
    }

    public Integer getLimitTimeAlertSecond() {
        return limitTimeAlertSecond;
    }

    public void setLimitTimeAlertSecond(Integer limitTimeAlertSecond) {
        this.limitTimeAlertSecond = limitTimeAlertSecond;
        if (limitTimeAlertSecond != null) {
            putBodyParameter("limitTimeAlertSecond", limitTimeAlertSecond);
        }
    }

    public String getLimitTimeFile() {
        return limitTimeFile;
    }

    public void setLimitTimeFile(String limitTimeFile) {
        this.limitTimeFile = limitTimeFile;
        if (limitTimeFile != null) {
            putBodyParameter("limitTimeFile", limitTimeFile);
        }
    }

    @Override
    public Class<ConsultThreewayResponse> getResponseClass() {
        return ConsultThreewayResponse.class;
    }
}
