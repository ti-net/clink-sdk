package com.tinet.clink.openapi.model;

/**
 * 新增话机返回对象
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class CreateExtenResultModel {

    /**
     * 话机号码
     */
    private String extenNumber;

    /**
     * 话机密码，不回显明文
     */
    private String password;

    /**
     * 话机区号
     */
    private String areaCode;

    /**
     * 话机类型，1: 分机， 2: 软电话
     */
    private Integer type;

    /**
     * 语音编码，1: G729， 2: [G729,alaw,ulaw]， 3: [alaw,ulaw,G729]
     */
    private Integer allow;

    public String getExtenNumber() {
        return extenNumber;
    }

    public void setExtenNumber(String extenNumber) {
        this.extenNumber = extenNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAllow() {
        return allow;
    }

    public void setAllow(Integer allow) {
        this.allow = allow;
    }
}