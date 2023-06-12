package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.ListHistoryCdrsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * 查询历史通话记录列表请求
 *
 * @author yinzk
 * @date 2023/6/9
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

    /**
     * 偏移量，范围 0-99990。默认值为 0，但limit + offset 不允许超过100000
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100。默认值为 10，但limit + offset 不允许超过100000
     */
    private Integer limit;


    public ListHistoryCdrsRequest() {
        super(PathEnum.ListHistoryCdrs.value(), HttpMethodType.GET);
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (Objects.nonNull(startTime)) {
            this.putQueryParameter("startTime", startTime);
        }
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (Objects.nonNull(endTime)) {
            this.putQueryParameter("endTime", endTime);
        }
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        if (Objects.nonNull(customerNumber)) {
            this.putQueryParameter("customerNumber", customerNumber);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            this.putQueryParameter("cno", cno);
        }
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
        if (Objects.nonNull(hotline)) {
            this.putQueryParameter("hotline", hotline);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        if (Objects.nonNull(status)) {
            this.putQueryParameter("status", status);
        }
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
        if (Objects.nonNull(callType)) {
            this.putQueryParameter("callType", callType);
        }
    }

    public Integer getHiddenType() {
        return hiddenType;
    }

    public void setHiddenType(Integer hiddenType) {
        this.hiddenType = hiddenType;
        if (Objects.nonNull(hiddenType)) {
            this.putQueryParameter("hiddenType", hiddenType);
        }
    }

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
        if (Objects.nonNull(userField)) {
            this.putQueryParameter("userField", userField);
        }
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
        if (Objects.nonNull(mark)) {
            this.putQueryParameter("mark", mark);
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        if (Objects.nonNull(province)) {
            this.putQueryParameter("province", province);
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        if (Objects.nonNull(city)) {
            this.putQueryParameter("city", city);
        }
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (Objects.nonNull(mainUniqueId)) {
            this.putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
        if (Objects.nonNull(requestUniqueId)) {
            this.putQueryParameter("requestUniqueId", requestUniqueId);
        }

    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
        if (Objects.nonNull(clientNumber)) {
            this.putQueryParameter("clientNumber", clientNumber);
        }
    }

    public Integer getQueueAnswerInTime() {
        return queueAnswerInTime;
    }

    public void setQueueAnswerInTime(Integer queueAnswerInTime) {
        this.queueAnswerInTime = queueAnswerInTime;
        if (Objects.nonNull(queueAnswerInTime)) {
            this.putQueryParameter("queueAnswerInTime", queueAnswerInTime);
        }
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
        if (Objects.nonNull(evaluation)) {
            this.putQueryParameter("evaluation", evaluation);
        }
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
        if (Objects.nonNull(idType)) {
            this.putQueryParameter("idType", idType);
        }
    }

    public Integer getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Integer associatedId) {
        this.associatedId = associatedId;
        if (Objects.nonNull(associatedId)) {
            this.putQueryParameter("associatedId", associatedId);
        }
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
        if (Objects.nonNull(qno)) {
            this.putQueryParameter("qno", qno);
        }
    }

    public String getHotlineName() {
        return hotlineName;
    }

    public void setHotlineName(String hotlineName) {
        this.hotlineName = hotlineName;
        if (Objects.nonNull(hotlineName)) {
            this.putQueryParameter("hotlineName", hotlineName);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (Objects.nonNull(limit)) {
            this.putQueryParameter("limit", limit);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (Objects.nonNull(offset)) {
            this.putQueryParameter("offset", offset);
        }
    }

    @Override
    public Class<ListHistoryCdrsResponse> getResponseClass() {
        return ListHistoryCdrsResponse.class;
    }
}

