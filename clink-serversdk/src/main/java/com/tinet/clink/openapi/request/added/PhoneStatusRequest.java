package com.tinet.clink.openapi.request.added;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.added.PhoneStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 手机号码状态检测
 *
 * @author wangll
 * @date 2021-03-24
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  PhoneStatusRequest extends AbstractRequestModel<PhoneStatusResponse> {
    /**
     * 电话
     */
    private String tel;

    /**
     * 加密传输，0：不加密；1：加密，默认不加密。
     */
    private Integer encrypt;

    public PhoneStatusRequest() {
        super(PathEnum.PhoneStatus.value(), HttpMethodType.GET);
    }

    @Override
    public Class<PhoneStatusResponse> getResponseClass() {
        return PhoneStatusResponse.class;
    }

    public Integer getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
        putQueryParameter("encrypt", encrypt);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
        putQueryParameter("tel", tel);
    }
}
