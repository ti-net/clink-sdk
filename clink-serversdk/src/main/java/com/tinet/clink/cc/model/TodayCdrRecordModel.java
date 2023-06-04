package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 今日通话记录返回对象
 *
 * @author huwk
 * @date 2018/09/05
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodayCdrRecordModel {

    /**
     * 主通道唯一标识
     */
    private String mainUniqueId;
    /**
     * 通道唯一标识
     */
    private String uniqueId;
    /**
     * 呼叫类型，呼入、外呼
     */
    private String callType;
    /**
     * 自定义字段
     */
    private String cdrUserField;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 客户所在区号
     */
    private String customerAreaCode;
    /**
     * 客户所在城市
     */
    private String customerCity;
    /**
     * 客户电话号码
     */
    private String customerNumber;
    /**
     * 加密后的客户电话号码
     */
    private String customerNumberEncrypt;
    /**
     * 用户电话号码类型
     */
    private Integer customerNumberType;
    /**
     * 客户所在邮编
     */
    private String customerPostCode;
    /**
     * 客户所在省份
     */
    private String customerProvince;
    /**
     * 客户为VIP
     */
    private Integer customerVip;
    /**
     * 热线号码
     */
    private String hotline;
    /**
     * 中继号码
     */
    private String numberTrunk;
    /**
     * 外呼外显号码
     */
    private String obCLid;
    /**
     * 队列名称
     */
    private String qname;
    /**
     * 队列编号
     */
    private String qno;
    /**
     * 语音导航Id
     */
    private String ivrId;
    /**
     * 语音导航名称
     */
    private String ivrName;
    /**
     * 通话开始时间
     */
    private Long startTime;

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCdrUserField() {
        return cdrUserField;
    }

    public void setCdrUserField(String cdrUserField) {
        this.cdrUserField = cdrUserField;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCustomerAreaCode() {
        return customerAreaCode;
    }

    public void setCustomerAreaCode(String customerAreaCode) {
        this.customerAreaCode = customerAreaCode;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
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

    public Integer getCustomerNumberType() {
        return customerNumberType;
    }

    public void setCustomerNumberType(Integer customerNumberType) {
        this.customerNumberType = customerNumberType;
    }

    public String getCustomerPostCode() {
        return customerPostCode;
    }

    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public Integer getCustomerVip() {
        return customerVip;
    }

    public void setCustomerVip(Integer customerVip) {
        this.customerVip = customerVip;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getNumberTrunk() {
        return numberTrunk;
    }

    public void setNumberTrunk(String numberTrunk) {
        this.numberTrunk = numberTrunk;
    }

    public String getObCLid() {
        return obCLid;
    }

    public void setObCLid(String obCLid) {
        this.obCLid = obCLid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getIvrId() {
        return ivrId;
    }

    public void setIvrId(String ivrId) {
        this.ivrId = ivrId;
    }

    public String getIvrName() {
        return ivrName;
    }

    public void setIvrName(String ivrName) {
        this.ivrName = ivrName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}

