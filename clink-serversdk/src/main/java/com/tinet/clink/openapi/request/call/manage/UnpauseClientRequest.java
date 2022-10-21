package com.tinet.clink.openapi.request.call.manage;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.manage.UnpauseClientResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 置闲请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  UnpauseClientRequest extends AbstractRequestModel<UnpauseClientResponse> {

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
