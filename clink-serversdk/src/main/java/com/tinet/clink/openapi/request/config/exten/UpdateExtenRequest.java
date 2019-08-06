package com.tinet.clink.openapi.request.config.exten;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.exten.UpdateExtenResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 修改话机请求
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class UpdateExtenRequest extends AbstractRequestModel<UpdateExtenResponse> {

    /**
     * 话机号码，不可修改
     */
    private String extenNumber;

    /**
     * 话机密码，3-6 位数字
     */
    private String password;

    /**
     * 话机区号，以 0 开头 3-4 位数字
     */
    private String areaCode;

    /**
     * 话机类型，1: 分机，2: 软电话
     */
    private Integer type;

    /**
     * 语音编码类型，: G729， 2: [G729,alaw,ulaw]， 3: [alaw,ulaw,G729]
     */
    private Integer allow;

    public String getExtenNumber() {
        return extenNumber;
    }

    public void setExtenNumber(String extenNumber) {
        this.extenNumber = extenNumber;
        if (extenNumber != null) {
            putQueryParameter("extenNumber", extenNumber);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        if (password != null) {
            putQueryParameter("password", password);
        }
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        if (areaCode != null) {
            putQueryParameter("areaCode", areaCode);
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            putQueryParameter("type", type);
        }
    }

    public Integer getAllow() {
        return allow;
    }

    public void setAllow(Integer allow) {
        this.allow = allow;
        if (allow != null) {
            putQueryParameter("allow", allow);
        }
    }

    public UpdateExtenRequest() {
        super(PathEnum.UpdateExten.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateExtenResponse> getResponseClass() {
        return UpdateExtenResponse.class;
    }
}