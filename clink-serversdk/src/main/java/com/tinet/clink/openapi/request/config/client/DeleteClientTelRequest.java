package com.tinet.clink.openapi.request.config.client;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.client.DeleteClientTelResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除座席下电话
 *
 * @author lizy
 * @date 2018/11/22
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteClientTelRequest extends AbstractRequestModel<DeleteClientTelResponse> {


    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席绑定过的电话
     */
    private String tel;

    public DeleteClientTelRequest() {
        super(PathEnum.DeleteClientTel.value(), HttpMethodType.POST);
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
        if (tel != null) {
            putBodyParameter("tel", tel);
        }
    }

    @Override
    public Class<DeleteClientTelResponse> getResponseClass() {
        return DeleteClientTelResponse.class;
    }
}
