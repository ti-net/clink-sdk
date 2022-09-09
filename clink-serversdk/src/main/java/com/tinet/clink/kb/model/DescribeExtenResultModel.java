package com.tinet.clink.kb.model;

/**
 * 话机详情对象
 */
public class DescribeExtenResultModel {

    /**
     * 话机号码
     */
    private String extenNumber;

    /**
     * 话机区号
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
     * 网络防抖开关 0：关闭；1：开启
     */
    private Integer jittBuffer;

    public String getExtenNumber() {
        return extenNumber;
    }

    public void setExtenNumber(String extenNumber) {
        this.extenNumber = extenNumber;
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

    public Integer getJittBuffer() {
        return jittBuffer;
    }

    public void setJittBuffer(Integer jittBuffer) {
        this.jittBuffer = jittBuffer;
    }
}