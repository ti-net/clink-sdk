package com.tinet.clink.cc.model;

/**
 * 未接来电记录
 *
 * @author Tinet-yinzk
 * @date 2023/6/9
 */
public class EnterpriseNoAnswerModel {

    /**
     * 主键
     */
    private Integer naId;

    /**
     * 座席编号
     */
    private String cno;

    /**
     * 客户号码
     */
    private String customerNumber;

    /**
     * 客户号码
     */
    private String customerNumberEncrypt;
    /**
     * 未接次数
     */
    private Integer count;

    /**
     * 是否处理
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 呼叫类型 ;枚举：呼入、webCall、点击外呼、预览外呼、预测外呼、主叫外呼、自助录音、发送传真、内部呼叫、预约回呼、呼转座席、转移、咨询、三方、监听、耳语、强插、抢线、转移、咨询、三方、监听、耳语、强插、呼转客户、呼转分机、呼转电话
     */
    private String callType;

    /**
     * 客户手机区号
     */
    private String customerAreaCode;

    /**
     * 客户是否为vip
     */
    private Integer customerVip;

    /**
     * 客户城市
     */
    private String customerCity;

    /**
     * 客户省份
     */
    private String customerProvince;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * obRememberOffline：外呼主叫记忆-离线未接听、obRememberBusy：外呼主叫记忆-忙碌未接听、
     * rna-响铃未接听、selfHandled：本座席处理
     */
    private String action;
    /**
     * 参考 action 字段
     */
    private String actionDest;

    /**
     * 是否为今天
     */
    private Boolean today;

    // 字段追加
    /**
     * 队列号
     */
    private String qno;
    /**
     * 最后未接来电的 未接原因。
     */
    private String reason;
    /**
     * 呼损类型：
     * 1.速挂;2.系统应答;3.系统未应答;4.人工未接听
     */
    private Integer callLossType;
    /**
     * 未接来电话单唯一标识集合
     */
    private String[] mainUniqueIds;
    /**
     * 最后未接来电的热线号
     */
    private String hotline;
    /**
     * 中继号码
     */
    private String numberTrunk;
    /**
     * 最后未接来电的创建时间
     */
    private Long endTime;
    /**
     * 最后一通未接来电，排队等待时长
     */
    private Integer queueWaitingDuration;
    /**
     * 最后一通未接来电，ivrId
     */
    private Integer ivrId;
    /**
     * 总时长 endTime-startTime
     */
    private Integer totalDuration;

    public Integer getNaId() {
        return naId;
    }

    public void setNaId(Integer naId) {
        this.naId = naId;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCustomerAreaCode() {
        return customerAreaCode;
    }

    public void setCustomerAreaCode(String customerAreaCode) {
        this.customerAreaCode = customerAreaCode;
    }

    public Integer getCustomerVip() {
        return customerVip;
    }

    public void setCustomerVip(Integer customerVip) {
        this.customerVip = customerVip;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionDest() {
        return actionDest;
    }

    public void setActionDest(String actionDest) {
        this.actionDest = actionDest;
    }

    public Boolean getToday() {
        return today;
    }

    public void setToday(Boolean today) {
        this.today = today;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getCallLossType() {
        return callLossType;
    }

    public void setCallLossType(Integer callLossType) {
        this.callLossType = callLossType;
    }

    public String[] getMainUniqueIds() {
        return mainUniqueIds;
    }

    public void setMainUniqueIds(String[] mainUniqueIds) {
        this.mainUniqueIds = mainUniqueIds;
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

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getQueueWaitingDuration() {
        return queueWaitingDuration;
    }

    public void setQueueWaitingDuration(Integer queueWaitingDuration) {
        this.queueWaitingDuration = queueWaitingDuration;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }
}