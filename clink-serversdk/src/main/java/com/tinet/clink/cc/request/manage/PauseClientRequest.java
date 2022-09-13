package com.tinet.clink.cc.request.manage;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.manage.PauseClientResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 置忙请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class PauseClientRequest extends AbstractRequestModel<PauseClientResponse> {

    /**
     * 被置忙的座席号
     */
    private String cno;

    /**
     * 触发此置忙事件的对象
     */
    private String operator;

    public PauseClientRequest() {
        super(PathEnum.PauseClient.value(), HttpMethodType.POST);
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
    public Class<PauseClientResponse> getResponseClass() {
        return PauseClientResponse.class;
    }
}
