package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.model.sqc.OriginData;
import com.tinet.smartlink.openapi.model.sqc.Record;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushCdrResponse;

import java.util.List;

/**
 * 推送CDR文件
 *
 * @author wenjd
 * @date 2019/04/11
 */
public class PushCdrRequest extends BaseRequest<PushCdrResponse> {


    /**
     * 记录唯一标识
     */
    private String uniqueId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * cdr文件类型
     * cdr_ib： 呼入主话单
     * cdr_ob_agent：外呼先呼座席侧主话单
     * cdr_ob_customer：外呼先呼客户侧主话单
     */
    private String cdrType;

    /**
     * 呼叫类型
     * 1：呼入
     * 2：网上400呼入
     * 3：点击外呼呼座席的通道
     */
    private Integer callType;

    /**
     * 录音来源类型、默认是类型 1
     * 1：平台自计费话单录音。（一般这种情况转写后不需要推送bigboss计费）
     * 2：质检平台第三方用户（90开头的企业）
     * 3：客户通过接口对接质检，（例如vnc的一些企业）
     * 4：页面上传录平台第三方客户话单音做质检的话单
     */
    private Integer cdrSource;

    /**
     * 座席电话号码
     */
    private String agentNumber;

    /**
     * 开始时间  (单位：秒)
     */
    private Long startTime;

    /**
     * 话单数据创建时间  (单位：秒)
     */
    private Long createTime;

    /**
     * 应答时间  (单位：秒)
     */
    private Long answerTime;

    /**
     * 结束时间  (单位：秒)
     */
    private Long endTime;

    /**
     * 接通时间  (单位：秒)
     */
    private Long bridgeTime;

    /**
     * 通话时长  (单位：秒)
     */
    private Integer bridgeDuration;

    /**
     * 总时长  (单位：秒)
     */
    private Integer totalDuration;


    /**
     * 接听状态
     * <p>
     * 呼入
     * 1：座席接听
     * 2：已呼叫座席，座席未接听
     * 3：系统接听
     * 4：系统未接-IVR配置错误
     * 5：系统未接-停机
     * 6：系统未接-欠费
     * 7：系统未接-黑名单
     * 8：系统未接-未注册
     * 9：系统未接-彩铃
     * 11：系统未接-呼叫超出营帐中设置的最大限制
     * 12：系统未接-客户呼入系统后在系统未应答前挂机
     * 13：其他错误
     * <p>
     * 外呼
     * 20：webcall，TTS合成失败
     * 21：webcall，客户未接
     * 22：webcall，客户接听
     * 23：webcall，已呼叫
     * 24：webcall，双方接听
     * 30：座席未接听
     * 31：座席接听，未呼叫客户
     * 32：座席接听，客户未接听
     * 33：双方接听
     * 40：预测外呼，客户未接听
     * 41：预测外呼，客户接听
     * 42：预测外呼，已呼叫
     * 43：预测外呼，双方接听
     * 50：主叫外呼接听
     * 51：主叫外呼，客户未接听
     * 52：主叫外呼，双方接听
     */
    private Integer status;


    /**
     * 挂机原因  0：客户未挂机   1：客户挂机
     */
    private Integer endReason;

    /**
     * 座席标识
     */
    private String cno;

    /**
     * 座席名称
     */
    private String agentName;

    /**
     * 队列标识
     */
    private String qno;

    /**
     * 队列名称
     */
    private String qname;

    /**
     * 分机号
     */
    private String exten;

    /**
     * 主话单和从话单的关连键
     */
    private String mainUniqueId;


    /**
     * 客户号码
     */
    private String customerNumber;

    /**
     * 客户号码类型
     */
    private String customerNumberType;

    /**
     * 客户号码区号
     */
    private String customerAreaCode;

    /**
     * 客户省份
     */
    private String customerProvince;

    /**
     * 客户城市
     */
    private String customerCity;

    /**
     * 加入队列时间
     */
    private Long joinQueueTime;

    /**
     * 录音文件地址
     */
    private String recordFile;

    /**
     * 中继号码
     */
    private String numberTrunk;

    /**
     * 热线号码
     */
    private String hotline;

    /**
     * 外显号码
     */
    private String clid;

    /**
     * 录音属性（包括录音的下载地址和分轨录音属于哪一次）
     */
    private List<Record> records;

    /**
     * 平台回调地址，不传值默认不回调
     */
    private String callbackUrl;

    /**
     * 可以自主选择转写引擎，不传值默认是天润自建引擎，
     */
    private String asrProvider;

    /**
     *  业务优先级
     */
    private String businessPriority;

    /**
     * 平台自定义字段传值list
     */
    private List<OriginData> originDataList;

    /**
     * 录音地址是否需要鉴权
     */
    private Boolean fileAuthFlag=false;

    /**
     * 悟空租车vnc 自定义信息适配专用字段
     */
    private String userField;

    /**
     * 声道话者标识：agent/client，数组第一位：左声道，数组第二位：右声道
     * 目前公有云同程数科客户在用
     */
    private String[] channelSpeaker;

    /**
     * 悟空租车vnc 自定义信息适配专用字段
     */
    private Boolean fileUrlEncrypt;

    /**
     * 过期时间
     */
    private Long expirationTime;

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
        if (userField != null) {
            putBodyParameter("userField", userField);
        }
    }

    public Boolean getFileAuthFlag() {
        return fileAuthFlag;
    }

    public void setFileAuthFlag(Boolean fileAuthFlag) {
        this.fileAuthFlag = fileAuthFlag;
        if (fileAuthFlag != null) {
            putBodyParameter("fileAuthFlag", fileAuthFlag);
        }
    }

    public String getBusinessPriority() {
        return businessPriority;
    }

    public void setBusinessPriority(String businessPriority) {
        this.businessPriority = businessPriority;
        if (businessPriority != null) {
            putBodyParameter("businessPriority", businessPriority);
        }
    }

    public Integer getCdrSource() {
        return cdrSource;
    }

    public void setCdrSource(Integer cdrSource) {
        this.cdrSource = cdrSource;
        if (cdrSource != null) {
            putBodyParameter("cdrSource", cdrSource);
        }
    }

    public List<OriginData> getOriginDataList() {
        return originDataList;
    }

    public void setOriginDataList(List<OriginData> originDataList) {
        this.originDataList = originDataList;
        if (originDataList != null) {
            putBodyParameter("originDataList", originDataList);
        }
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
        if (records != null) {
            putBodyParameter("records", records);
        }
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        if (callbackUrl != null) {
            putBodyParameter("callbackUrl", callbackUrl);
        }
    }

    public String getAsrProvider() {
        return asrProvider;
    }

    public void setAsrProvider(String asrProvider) {
        this.asrProvider = asrProvider;
        if (asrProvider != null) {
            putBodyParameter("asrProvider", asrProvider);
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putBodyParameter("uniqueId", uniqueId);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public String getCdrType() {
        return cdrType;
    }

    public void setCdrType(String cdrType) {
        this.cdrType = cdrType;
        if (cdrType != null) {
            putBodyParameter("cdrType", cdrType);
        }
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
        if (callType != null) {
            putBodyParameter("callType", callType);
        }
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
        if (agentNumber != null) {
            putBodyParameter("agentNumber", agentNumber);
        }
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putBodyParameter("startTime", startTime);
        }
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
        if (answerTime != null) {
            putBodyParameter("answerTime", answerTime);
        }
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putBodyParameter("endTime", endTime);
        }
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
        if(createTime != null){
            putBodyParameter("createTime", createTime);
        }
    }
    public Long getBridgeTime() {
        return bridgeTime;
    }

    public void setBridgeTime(Long bridgeTime) {
        this.bridgeTime = bridgeTime;
        if (bridgeTime != null) {
            putBodyParameter("bridgeTime", bridgeTime);
        }
    }

    public Integer getBridgeDuration() {
        return bridgeDuration;
    }

    public void setBridgeDuration(Integer bridgeDuration) {
        this.bridgeDuration = bridgeDuration;
        if (bridgeDuration != null) {
            putBodyParameter("bridgeDuration", bridgeDuration);
        }
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
        if (totalDuration != null) {
            putBodyParameter("totalDuration", totalDuration);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        if (status != null) {
            putBodyParameter("status", status);
        }
    }

    public Integer getEndReason() {
        return endReason;
    }

    public void setEndReason(Integer endReason) {
        this.endReason = endReason;
        if (endReason != null) {
            putBodyParameter("endReason", endReason);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
        if (agentName != null) {
            putBodyParameter("agentName", agentName);
        }
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
        if (qno != null) {
            putBodyParameter("qno", qno);
        }
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
        if (qname != null) {
            putBodyParameter("qname", qname);
        }
    }

    public String getExten() {
        return exten;
    }

    public void setExten(String exten) {
        this.exten = exten;
        if (exten != null) {
            putBodyParameter("exten", exten);
        }
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putBodyParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        if (customerNumber != null) {
            putBodyParameter("customerNumber", customerNumber);
        }
    }

    public String getCustomerNumberType() {
        return customerNumberType;
    }

    public void setCustomerNumberType(String customerNumberType) {
        this.customerNumberType = customerNumberType;
        if (customerNumberType != null) {
            putBodyParameter("customerNumberType", customerNumberType);
        }
    }

    public String getCustomerAreaCode() {
        return customerAreaCode;
    }

    public void setCustomerAreaCode(String customerAreaCode) {
        this.customerAreaCode = customerAreaCode;
        if (customerAreaCode != null) {
            putBodyParameter("customerAreaCode", customerAreaCode);
        }
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
        if (customerProvince != null) {
            putBodyParameter("customerProvince", customerProvince);
        }
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
        if (customerCity != null) {
            putBodyParameter("customerCity", customerCity);
        }
    }

    public Long getJoinQueueTime() {
        return joinQueueTime;
    }

    public void setJoinQueueTime(Long joinQueueTime) {
        this.joinQueueTime = joinQueueTime;
        if (joinQueueTime != null) {
            putBodyParameter("joinQueueTime", joinQueueTime);
        }
    }

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
        if (recordFile != null) {
            putBodyParameter("recordFile", recordFile);
        }
    }

    public String getNumberTrunk() {
        return numberTrunk;
    }

    public void setNumberTrunk(String numberTrunk) {
        this.numberTrunk = numberTrunk;
        if (numberTrunk != null) {
            putBodyParameter("numberTrunk", numberTrunk);
        }
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
        if (hotline != null) {
            putBodyParameter("hotline", hotline);
        }
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
        if (clid != null) {
            putBodyParameter("clid", clid);
        }
    }

    public String[] getChannelSpeaker() {
        return channelSpeaker;
    }

    public void setChannelSpeaker(String[] channelSpeaker) {
        this.channelSpeaker = channelSpeaker;
        if (channelSpeaker != null) {
            putBodyParameter("channelSpeaker", channelSpeaker);
        }
    }

    public Boolean getFileUrlEncrypt() {
        return fileUrlEncrypt;
    }

    public void setFileUrlEncrypt(Boolean fileUrlEncrypt) {
        this.fileUrlEncrypt = fileUrlEncrypt;
        if (fileUrlEncrypt != null) {
            putBodyParameter("channelSpeaker", fileUrlEncrypt);
        }
    }

    public PushCdrRequest() {
        super("/sqc/cdr", HttpMethodType.POST,"2019-07-22");
    }

    @Override
    public Class<PushCdrResponse> getResponseClass() {
        return PushCdrResponse.class;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
        if (expirationTime != null) {
            putBodyParameter("expirationTime", expirationTime);
        }
    }
    public Long getExpirationTime() {
        return expirationTime;
    }

}
