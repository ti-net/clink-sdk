package com.tinet.clink.openapi.request.call.manage;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.manage.WhisperResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 耳语请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class WhisperRequest extends AbstractRequestModel<WhisperResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 被耳语耳语类型
     */
    private Integer whisperType;

    /**
     * 耳语对象号码
     */
    private String whisperNumber;

    public WhisperRequest() {
        super(PathEnum.Whisper.value(), HttpMethodType.POST);
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

    public Integer getWhisperType() {
        return whisperType;
    }

    public void setWhisperType(Integer whisperType) {
        this.whisperType = whisperType;
        if (whisperType != null) {
            putBodyParameter("whisperType", whisperType);
        }
    }

    public String getWhisperNumber() {
        return whisperNumber;
    }

    public void setWhisperNumber(String whisperNumber) {
        this.whisperNumber = whisperNumber;
        if (whisperNumber != null) {
            putBodyParameter("whisperNumber", whisperNumber);
        }
    }

    @Override
    public Class<WhisperResponse> getResponseClass() {
        return WhisperResponse.class;
    }
}
