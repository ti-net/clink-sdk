package com.tinet.clink.openapi.request.call.manage;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.manage.PauseClientResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 置忙请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  PauseClientRequest extends AbstractRequestModel<PauseClientResponse> {

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
