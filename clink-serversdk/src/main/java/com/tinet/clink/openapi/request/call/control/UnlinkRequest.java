package com.tinet.clink.openapi.request.call.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.UnlinkResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 挂机请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  UnlinkRequest extends AbstractRequestModel<UnlinkResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;
    public UnlinkRequest() {
        super(PathEnum.Unlink.value(), HttpMethodType.POST);
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

    @Override
    public Class<UnlinkResponse> getResponseClass() {
        return UnlinkResponse.class;
    }
}
