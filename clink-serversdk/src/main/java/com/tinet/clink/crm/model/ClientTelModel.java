package com.tinet.clink.crm.model;


/**
 * @author lizy
 * @date 2018/09/14
 */
public class ClientTelModel {
    /**
     * 电话号码
     */
    private String tel;

    /**
     * 电话类型 1:固话 2:手机 3:分机 4:软电话
     */
    private Integer telType;

    /**
     * 是否被绑定 0:否 1:是
     */
    private Integer isBind;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getTelType() {
        return telType;
    }

    public void setTelType(Integer telType) {
        this.telType = telType;
    }

    public Integer getIsBind() {
        return isBind;
    }

    public void setIsBind(Integer isBind) {
        this.isBind = isBind;
    }
}
