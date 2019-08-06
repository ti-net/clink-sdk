package com.tinet.clink.openapi.request.call.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.PauseResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 置忙请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class PauseRequest extends AbstractRequestModel<PauseResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 置忙原因，如果是自定义状态，则状态描述需包含在企业自定义的置忙状态内，默认描述为置忙
     */
    private String description;

    public PauseRequest() {
        super(PathEnum.Pause.value(), HttpMethodType.POST);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if (description != null) {
            putBodyParameter("description", description);
        }
    }

    @Override
    public Class<PauseResponse> getResponseClass() {
        return PauseResponse.class;
    }
}
