package com.tinet.clink.cc.request.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.RefuseResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 拒接请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class RefuseRequest extends AbstractRequestModel<RefuseResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;
    public RefuseRequest() {
        super(PathEnum.Refuse.value(), HttpMethodType.POST);
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
    public Class<RefuseResponse> getResponseClass() {
        return RefuseResponse.class;
    }
}
