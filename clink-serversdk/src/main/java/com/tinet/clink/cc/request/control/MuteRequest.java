package com.tinet.clink.cc.request.control;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.control.MuteResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 静音请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class MuteRequest extends AbstractRequestModel<MuteResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 静音方向，取值范围：in、out、all
     */
    private String direction;

    public MuteRequest() {
        super(PathEnum.Mute.value(), HttpMethodType.POST);
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
    public Class<MuteResponse> getResponseClass() {
        return MuteResponse.class;
    }
}
