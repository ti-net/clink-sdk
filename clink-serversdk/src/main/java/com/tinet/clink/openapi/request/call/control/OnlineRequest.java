package com.tinet.clink.openapi.request.call.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.OnlineResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 上线请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class OnlineRequest extends AbstractRequestModel<OnlineResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 电话类型，1:电话；2:分机；3:软电话
     */
    private Integer bindType;

    /**
     * 绑定电话
     */
    private String bindTel;

    /**
     * 登录状态，1:空闲；2:置忙，默认值为1
     */
    private Integer status;

    /**
     * 状态描述，当status为2时需要给定参数值，描述需包含在企业自定义的置忙状态内
     */
    private String decription;


    public OnlineRequest() {
        super(PathEnum.Online.value(), HttpMethodType.POST);
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

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
        if (bindType != null) {
            putBodyParameter("bindType", bindType);
        }
    }

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
        if (bindTel != null) {
            putBodyParameter("bindTel", bindTel);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        if (status != null) {
            putBodyParameter("status", status);
        }
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
        if (decription != null) {
            putBodyParameter("decription", decription);
        }
    }

    @Override
    public Class<OnlineResponse> getResponseClass() {
        return OnlineResponse.class;
    }
}
