package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for:
 * 通话记录
 *
 * @author : Tinet-yinzk
 * @date : 2023/6/5 00:08
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CdrRecordModel {
    /**
     * 通话记录主通道唯一标识
     */
    private String mainUniqueId;
    /**
     * 通话记录详情唯一标识
     */
    private String uniqueId;
    /**
     * 通话记录 Id
     */
    private String callId;
    /**
     * 呼叫类型
     */
    private String callType;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 接通时间
     */
    private Long bridgeTime;
    /**
     * 通话时长
     */
    private Integer bridgeDuration;
    /**
     * 总时长
     */
    private Integer totalDuration;
    /**
     * 客户来电号码，带区号
     */
    private String customerNumber;
    /**
     * 客户来电号码加密串
     */
    private String customerNumberEncrypt;
    /**
     * 客户号码区号
     */
    private String customerAreaCode;
    /**
     * 客户来电省份
     */
    private String customerProvince;
    /**
     * 客户来电城市
     */
    private String customerCity;
    /**
     * 热线号码
     */
    private String hotline;
    /**
     * 呼叫结果
     */
    private String sipCause;
    /**
     * 挂断方
     */
    private String endReason;
    /**
     * 座席电话
     */
    private String clientNumber;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 座席名称
     */
    private String clientName;
    /**
     * 接听状态。取值范围如下：0: 全部 1: 座席接听 2: 已呼叫座席，座席未接听  3: 系统接听 4: 系统未接听-IVR配置错误 5: 系统未接听-停机
     * 6: 系统未接听-欠费 7: 系统未接听-黑名单 8: 系统未接听-未注册 9: 系统未接听-彩铃 10: 系统未接听-网上400
     * 11: 系统未接听-呼叫超出营帐中设置的最大限制   12: 系统未接听-客户呼入系统后在系统未应答前挂机 13: 其他错误 默认值为0
     */
    private String status;
    /**
     * IVR名称
     */
    private String ivrName;
    /**
     * 队列号
     */
    private String qno;
    /**
     * 标记。取值范围如下： 为空表示全部， 1:留言 2:转移 3:咨询 4:三方 5:传真接收 6:会议 7:交互 8:IVR中放弃 9:已进入IVR 10:未进入IVR 11:队列中放弃 12:队列中溢出 注： 其中mark值2,3,4,7仅在status=1(座席接听)时有效， mark值1,5,6,8,9,10,11,12在status=3(系统接听)时有效
     */
    private Integer mark;
    /**
     * 备注
     */
    private String markData;
    /**
     * 满意度按键值；当键值为 null 时，说明用户无按键操作
     */
    private Integer keys;
    /**
     * 虚拟号码
     */
    private String xnumber;
    /**
     * 标签
     */
    private String[] tags;
    /**
     * 客户为VIP,0:否，1：是
     */
    private Integer customerVip;
    /**
     * 客户响铃时间
     */
    private Long customerRingingTime;
    /**
     * 座席响铃时间
     */
    private Long clientRingingTime;
    /**
     * 排队时长
     */
    private Long ibWaitDuration;
    /**
     * 队列名称
     */
    private String qname;
    /**
     * 外呼任务id
     */
    private Integer taskId;
    /**
     * 外呼任务名称
     */
    private String taskName;
    /**
     * 外呼任务详情id
     */
    private Integer taskInventoryId;
    /**
     * 进入队列时间
     */
    private Long joinQueueTime;
    /**
     * 离开队列时间
     */
    private Long leaveQueueTime;
    /**
     * 满意度值
     */
    private Integer investigationKeys;
    /**
     * 座席振铃时长
     */
    private Integer ibRingingDuration;
    /**
     * 语音播报时长
     */
    private Integer sayVoiceDuration;
    /**
     * 座席振铃时间
     */
    private Long ringTime;
    /**
     * 队列及时应答:0: 否 1: 是 2: -
     */
    private Integer queueAnswerInTime;
    /**
     * 是否邀评 1: 是  0: 否
     */
    private Integer evaluation;
    /**
     * 中继号码
     */
    private String numberTrunk;
    /**
     * 客户所在邮编
     */
    private String customerPostCode;
    /**
     * 首次进入队列时间
     */
    private Long firstJoinQueueTime;
    /**
     * 录音文件名
     */
    private String recordFile;
    /**
     * 接听状态映射
     */
    private String statusCode;

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

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getBridgeTime() {
        return bridgeTime;
    }

    public void setBridgeTime(Long bridgeTime) {
        this.bridgeTime = bridgeTime;
    }

    public Integer getBridgeDuration() {
        return bridgeDuration;
    }

    public void setBridgeDuration(Integer bridgeDuration) {
        this.bridgeDuration = bridgeDuration;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
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

    public String getCustomerAreaCode() {
        return customerAreaCode;
    }

    public void setCustomerAreaCode(String customerAreaCode) {
        this.customerAreaCode = customerAreaCode;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getSipCause() {
        return sipCause;
    }

    public void setSipCause(String sipCause) {
        this.sipCause = sipCause;
    }

    public String getEndReason() {
        return endReason;
    }

    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIvrName() {
        return ivrName;
    }

    public void setIvrName(String ivrName) {
        this.ivrName = ivrName;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getMarkData() {
        return markData;
    }

    public void setMarkData(String markData) {
        this.markData = markData;
    }

    public Integer getKeys() {
        return keys;
    }

    public void setKeys(Integer keys) {
        this.keys = keys;
    }

    public String getXnumber() {
        return xnumber;
    }

    public void setXnumber(String xnumber) {
        this.xnumber = xnumber;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Integer getCustomerVip() {
        return customerVip;
    }

    public void setCustomerVip(Integer customerVip) {
        this.customerVip = customerVip;
    }

    public Long getCustomerRingingTime() {
        return customerRingingTime;
    }

    public void setCustomerRingingTime(Long customerRingingTime) {
        this.customerRingingTime = customerRingingTime;
    }

    public Long getClientRingingTime() {
        return clientRingingTime;
    }

    public void setClientRingingTime(Long clientRingingTime) {
        this.clientRingingTime = clientRingingTime;
    }

    public Long getIbWaitDuration() {
        return ibWaitDuration;
    }

    public void setIbWaitDuration(Long ibWaitDuration) {
        this.ibWaitDuration = ibWaitDuration;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskInventoryId() {
        return taskInventoryId;
    }

    public void setTaskInventoryId(Integer taskInventoryId) {
        this.taskInventoryId = taskInventoryId;
    }

    public Long getJoinQueueTime() {
        return joinQueueTime;
    }

    public void setJoinQueueTime(Long joinQueueTime) {
        this.joinQueueTime = joinQueueTime;
    }

    public Long getLeaveQueueTime() {
        return leaveQueueTime;
    }

    public void setLeaveQueueTime(Long leaveQueueTime) {
        this.leaveQueueTime = leaveQueueTime;
    }

    public Integer getInvestigationKeys() {
        return investigationKeys;
    }

    public void setInvestigationKeys(Integer investigationKeys) {
        this.investigationKeys = investigationKeys;
    }

    public Integer getIbRingingDuration() {
        return ibRingingDuration;
    }

    public void setIbRingingDuration(Integer ibRingingDuration) {
        this.ibRingingDuration = ibRingingDuration;
    }

    public Integer getSayVoiceDuration() {
        return sayVoiceDuration;
    }

    public void setSayVoiceDuration(Integer sayVoiceDuration) {
        this.sayVoiceDuration = sayVoiceDuration;
    }

    public Long getRingTime() {
        return ringTime;
    }

    public void setRingTime(Long ringTime) {
        this.ringTime = ringTime;
    }

    public Integer getQueueAnswerInTime() {
        return queueAnswerInTime;
    }

    public void setQueueAnswerInTime(Integer queueAnswerInTime) {
        this.queueAnswerInTime = queueAnswerInTime;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public String getNumberTrunk() {
        return numberTrunk;
    }

    public void setNumberTrunk(String numberTrunk) {
        this.numberTrunk = numberTrunk;
    }

    public String getCustomerPostCode() {
        return customerPostCode;
    }

    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    public Long getFirstJoinQueueTime() {
        return firstJoinQueueTime;
    }

    public void setFirstJoinQueueTime(Long firstJoinQueueTime) {
        this.firstJoinQueueTime = firstJoinQueueTime;
    }

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
