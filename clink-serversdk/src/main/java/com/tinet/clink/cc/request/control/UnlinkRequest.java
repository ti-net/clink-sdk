package com.tinet.clink.cc.request.control;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.control.UnlinkResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 挂机请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class UnlinkRequest extends AbstractRequestModel<UnlinkResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;
    public UnlinkRequest() {
        super(PathEnum.Unlink.value(), HttpMethodType.POST);
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
    public Class<UnlinkResponse> getResponseClass() {
        return UnlinkResponse.class;
    }
}
