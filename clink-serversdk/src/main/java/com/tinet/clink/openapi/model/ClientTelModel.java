package com.tinet.clink.openapi.model;


/**
 * @author lizy
 * @date 2018/09/14
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ClientTelModel {
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
