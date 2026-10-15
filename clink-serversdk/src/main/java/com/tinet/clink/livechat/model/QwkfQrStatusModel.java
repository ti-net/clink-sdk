package com.tinet.clink.livechat.model;

/**
 * 企微客服二维码状态
 */
public class QwkfQrStatusModel {

    private Integer traceIdState;
    private String qrCodeUrl;
    private Integer qrCodeStatus;
    private String qrCodeKey;
    private String deviceId;
    private Object platformType;
    private String remark;

    public Integer getTraceIdState() {
        return traceIdState;
    }

    public void setTraceIdState(Integer traceIdState) {
        this.traceIdState = traceIdState;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public Integer getQrCodeStatus() {
        return qrCodeStatus;
    }

    public void setQrCodeStatus(Integer qrCodeStatus) {
        this.qrCodeStatus = qrCodeStatus;
    }

    public String getQrCodeKey() {
        return qrCodeKey;
    }

    public void setQrCodeKey(String qrCodeKey) {
        this.qrCodeKey = qrCodeKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Object getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Object platformType) {
        this.platformType = platformType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
