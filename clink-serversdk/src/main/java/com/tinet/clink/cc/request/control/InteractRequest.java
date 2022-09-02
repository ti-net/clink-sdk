package com.tinet.clink.cc.request.control;

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
    private Integer ivrId;

    /**
     * 交互对象号码
     */
    private String ivrNode;


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

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
        if (ivrId != null) {
            putBodyParameter("ivrId", ivrId);
        }
    }

    public String getIvrNode() {
        return ivrNode;
    }

    public void setIvrNode(String ivrNode) {
        this.ivrNode = ivrNode;
        if (ivrNode != null) {
            putBodyParameter("ivrNode", ivrNode);
        }
    }

    @Override
    public Class<InteractResponse> getResponseClass() {
        return InteractResponse.class;
    }
}
