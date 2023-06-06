package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.ListHistoryCdrsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 查询历史通话记录列表请求
 *
 * @author huwk
 * @date 2018/10/23
 **/
public class ListHistoryCdrsRequest extends AbstractRequestModel<ListHistoryCdrsResponse> {

    /**
     * 开始时间戳，单位：s
     */
    private Long startTime;
    /**
     * 结束时间戳，单位：s
     */
    private Long endTime;
    /**
     * 客户号码
     */
    private String customerNumber;
    /**
     * 座席号
     */
    private String cno;

    /**
     * 热线号码
     */
    private String hotline;

    /**
     * 接听状态
     */
    private Integer status = 0;

    /**
     * 呼叫类型 ib-呼入 or ob-外呼
     */
    private String callType;

    /**
     * 是否隐藏号码 0 不隐藏，1隐藏中间四位 2隐藏最后八位 3隐藏全部号码 4隐藏最后四位 默认是0
     */
    private Integer hiddenType = 0;

    /**
     * 自定义字段
     */
    private String userField;

    /**
     * 标记
     */
    private Integer mark;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    /**
     * 请求唯一标识
     */
    private String requestUniqueId;

    /**
     * 座席电话
     */
    private String clientNumber;

    /**
     * 队列及时应答
     */
    private Integer queueAnswerInTime;

    /**
     * 是否邀评
     */
    private Integer evaluation;

    /**
     * 业务ID类型
     */
    private Integer idType;

    /**
     * 业务关联ID
     */
    private Integer associatedId;

    /**
     * 队列号
     */
    private String qno;


    /**
     * 热线别名
     */
    private String hotlineName;


    public ListHistoryCdrsRequest() {
        super(PathEnum.ListHistoryCdrs.value(), HttpMethodType.GET);
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

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Integer getHiddenType() {
        return hiddenType;
    }

    public void setHiddenType(Integer hiddenType) {
        this.hiddenType = hiddenType;
    }

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
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

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Integer getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Integer associatedId) {
        this.associatedId = associatedId;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getHotlineName() {
        return hotlineName;
    }

    public void setHotlineName(String hotlineName) {
        this.hotlineName = hotlineName;
    }

    @Override
    public Class<ListHistoryCdrsResponse> getResponseClass() {
        return ListHistoryCdrsResponse.class;
    }
}

