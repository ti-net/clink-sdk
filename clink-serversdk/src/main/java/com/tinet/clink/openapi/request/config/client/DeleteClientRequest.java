package com.tinet.clink.openapi.request.config.client;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.client.DeleteClientResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除座席请求
 *
 * @author lizy
 * @date 2018/10/11
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteClientRequest extends AbstractRequestModel<DeleteClientResponse> {

    /**
     * 座席号
     */
    private String cno;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public DeleteClientRequest() {
        super(PathEnum.DeleteClient.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteClientResponse> getResponseClass() {
        return DeleteClientResponse.class;
    }
}
