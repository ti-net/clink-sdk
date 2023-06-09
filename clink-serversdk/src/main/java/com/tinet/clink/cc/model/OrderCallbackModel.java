package com.tinet.clink.cc.model;

/**
 * 预约回呼记录
 */
public class OrderCallbackModel {

    private Integer id;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 唯一标识
     */
    private String mainUniqueId;

    /**
     * 队列号
     */
    private String qno;

    /**
     * 主叫号码
     */
    private String tel;

    /**
     * 客户号码
     */
    private String customerNumber;

    /**
     * 加密号码
     */
    private String customerNumberEncrypt;

    /**
     * 是否处理 0:还没呼叫  1:已呼叫
     */
    private Integer isCall;

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 地区
     */
    private String areaCode;

    /**
     * 回呼座席工号
     */
    private String cno;

    /**
     * 座席回呼时间
     */
    private Long callBackTime;

    /**
     * 创建时间
     */
    private Long createTime;


    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 座席名称
     */
    private String cnoName;

    /**
     * 客户名称
     */
    private String customerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumberEncrypt() {
        return customerNumberEncrypt;
    }

    public void setCustomerNumberEncrypt(String customerNumberEncrypt) {
        this.customerNumberEncrypt = customerNumberEncrypt;
    }

    public Integer getIsCall() {
        return isCall;
    }

    public void setIsCall(Integer isCall) {
        this.isCall = isCall;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public Long getCallBackTime() {
        return callBackTime;
    }

    public void setCallBackTime(Long callBackTime) {
        this.callBackTime = callBackTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCnoName() {
        return cnoName;
    }

    public void setCnoName(String cnoName) {
        this.cnoName = cnoName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}