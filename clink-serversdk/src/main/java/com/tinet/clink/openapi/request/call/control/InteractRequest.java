package com.tinet.clink.openapi.request.call.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.InteractResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 交互请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class InteractRequest extends AbstractRequestModel<InteractResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 交互类型，0:语音导航节点；1:语音导航名称
     */
    private Integer interactType;

    /**
     * 交互对象号码
     */
    private String interactNumber;


    public InteractRequest() {
        super(PathEnum.Interact.value(), HttpMethodType.POST);
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

    public Integer getInteractType() {
        return interactType;
    }

    public void setInteractType(Integer interactType) {
        this.interactType = interactType;
        if (interactType != null) {
            putBodyParameter("interactType", interactType);
        }
    }

    public String getInteractNumber() {
        return interactNumber;
    }

    public void setInteractNumber(String interactNumber) {
        this.interactNumber = interactNumber;
        if (interactNumber != null) {
            putBodyParameter("interactNumber", interactNumber);
        }
    }

    @Override
    public Class<InteractResponse> getResponseClass() {
        return InteractResponse.class;
    }
}
