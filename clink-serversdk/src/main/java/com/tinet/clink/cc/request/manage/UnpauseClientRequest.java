package com.tinet.clink.cc.request.manage;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.manage.UnpauseClientResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 置闲请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class UnpauseClientRequest extends AbstractRequestModel<UnpauseClientResponse> {

    /**
     * 被置闲的座席号
     */
    private String cno;

    /**
     * 触发此置闲事件的对象
     */
    private String operator;

    public UnpauseClientRequest() {
        super(PathEnum.UnpauseClient.value(), HttpMethodType.POST);
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
        if (operator != null) {
            putBodyParameter("operator", operator);
        }
    }

    @Override
    public Class<UnpauseClientResponse> getResponseClass() {
        return UnpauseClientResponse.class;
    }
}
