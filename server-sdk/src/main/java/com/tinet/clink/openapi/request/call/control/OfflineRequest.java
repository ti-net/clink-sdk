package com.tinet.clink.openapi.request.call.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.OfflineResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 下线请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class OfflineRequest extends AbstractRequestModel<OfflineResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 是否下线同时解绑电话，0:不解绑；1:解绑，默认值为0
     */
    private Integer unbindTel;

    public OfflineRequest() {
        super(PathEnum.Offline.value(), HttpMethodType.POST);
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

    public Integer getUnbindTel() {
        return unbindTel;
    }

    public void setUnbindTel(Integer unbindTel) {
        this.unbindTel = unbindTel;
        if (unbindTel != null) {
            putBodyParameter("unbindTel", unbindTel);
        }
    }

    @Override
    public Class<OfflineResponse> getResponseClass() {
        return OfflineResponse.class;
    }
}
