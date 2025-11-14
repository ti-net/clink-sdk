package com.tinet.clink.cc.request.control;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.control.InteractResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


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
     * 语音导航名称
     */
    private String ivrName;

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

    public String getIvrName() {
        return ivrName;
    }

    public void setIvrName(String ivrName) {
        this.ivrName = ivrName;
        if (ivrName != null) {
            putBodyParameter("ivrName", ivrName);
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
