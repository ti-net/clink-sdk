package com.tinet.clink.cc.request.manage;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.manage.BargeResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 强插请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class BargeRequest extends AbstractRequestModel<BargeResponse> {

    /**
     * 被强插座席号
     */
    private String cno;

    /**
     * 强插类型
     */
    private Integer bargeType;

    /**
     * 强插对象电话
     */
    private String bargeNumber;

    public BargeRequest() {
        super(PathEnum.Barge.value(), HttpMethodType.POST);
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

    public Integer getBargeType() {
        return bargeType;
    }

    public void setBargeType(Integer bargeType) {
        this.bargeType = bargeType;
        if (bargeType != null) {
            putBodyParameter("bargeType", bargeType);
        }
    }

    public String getBargeNumber() {
        return bargeNumber;
    }

    public void setBargeNumber(String bargeNumber) {
        this.bargeNumber = bargeNumber;
        if (bargeNumber != null) {
            putBodyParameter("bargeNumber", bargeNumber);
        }
    }

    @Override
    public Class<BargeResponse> getResponseClass() {
        return BargeResponse.class;
    }
}
