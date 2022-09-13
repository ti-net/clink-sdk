package com.tinet.clink.cc.request.control;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.control.UnholdResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 取消保持请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class UnholdRequest extends AbstractRequestModel<UnholdResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    public UnholdRequest() {
        super(PathEnum.Unhold.value(), HttpMethodType.POST);
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
    public Class<UnholdResponse> getResponseClass() {
        return UnholdResponse.class;
    }
}
