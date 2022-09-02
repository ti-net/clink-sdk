package com.tinet.clink.cc.request.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.UnmuteResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 取消静音请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class UnmuteRequest extends AbstractRequestModel<UnmuteResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 静音方向，取值范围：in、out、all
     */
    private String direction;

    public UnmuteRequest() {
        super(PathEnum.Unmute.value(), HttpMethodType.POST);
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
        if (direction != null) {
            putBodyParameter("direction", direction);
        }
    }

    @Override
    public Class<UnmuteResponse> getResponseClass() {
        return UnmuteResponse.class;
    }
}
