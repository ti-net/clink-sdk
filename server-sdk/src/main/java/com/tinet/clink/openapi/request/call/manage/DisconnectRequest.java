package com.tinet.clink.openapi.request.call.manage;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.manage.DisconnectResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 强拆请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class DisconnectRequest extends AbstractRequestModel<DisconnectResponse> {

    /**
     * 被强拆座席号
     */
    private String cno;

    /**
     * 强拆类型
     */
    private Integer disconnectType;

    /**
     * 强拆对象号码
     */
    private String disconnectNumber;

    public DisconnectRequest() {
        super(PathEnum.Disconnect.value(), HttpMethodType.POST);
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

    public Integer getDisconnectType() {
        return disconnectType;
    }

    public void setDisconnectType(Integer disconnectType) {
        this.disconnectType = disconnectType;
        if (disconnectType != null) {
            putBodyParameter("disconnectType", disconnectType);
        }
    }

    public String getDisconnectNumber() {
        return disconnectNumber;
    }

    public void setDisconnectNumber(String disconnectNumber) {
        this.disconnectNumber = disconnectNumber;
        if (disconnectNumber != null) {
            putBodyParameter("disconnectNumber", disconnectNumber);
        }
    }

    @Override
    public Class<DisconnectResponse> getResponseClass() {
        return DisconnectResponse.class;
    }
}
