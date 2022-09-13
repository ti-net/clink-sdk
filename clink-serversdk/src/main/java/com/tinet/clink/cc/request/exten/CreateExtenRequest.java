package com.tinet.clink.cc.request.exten;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.exten.CreateExtenResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 新增话机请求
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class CreateExtenRequest extends AbstractRequestModel<CreateExtenResponse> {

    /**
     * 话机号码，3-6 位数字，要求唯一
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
     * 话机类型，1: 分机， 2: 软电话
     */
    private Integer type;

    /**
     * 语音编码类型，1: G729， 2: [G729,alaw,ulaw]， 3: [alaw,ulaw,G729]
     */
    private Integer allow;

    /**
     * 是否允许主叫外呼，1：允许，0：不允许
     */
    private Integer isDirect;

    /**
     * 网络防抖开关 0：关闭；1：开启
     */
    private Integer jittBuffer;

    public CreateExtenRequest() {
        super(PathEnum.CreateExten.value(), HttpMethodType.POST);
    }

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

    public Integer getIsDirect() {
        return isDirect;
    }

    public void setIsDirect(Integer isDirect) {
        this.isDirect = isDirect;
        if (isDirect != null) {
            putQueryParameter("isDirect", isDirect);
        }
    }

    public Integer getJittBuffer() {
        return jittBuffer;
    }

    public void setJittBuffer(Integer jittBuffer) {
        this.jittBuffer = jittBuffer;
        if (jittBuffer != null) {
            putQueryParameter("jittBuffer", jittBuffer);
        }
    }

    @Override
    public Class<CreateExtenResponse> getResponseClass() {
        return CreateExtenResponse.class;
    }
}