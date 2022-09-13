package com.tinet.clink.cc.request.control;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.control.CalloutCancelResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 外呼取消请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class CalloutCancelRequest extends AbstractRequestModel<CalloutCancelResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    public CalloutCancelRequest() {
        super(PathEnum.CalloutCancel.value(), HttpMethodType.POST);
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
    public Class<CalloutCancelResponse> getResponseClass() {
        return CalloutCancelResponse.class;
    }
}
