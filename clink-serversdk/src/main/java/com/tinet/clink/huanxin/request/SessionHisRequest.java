package com.tinet.clink.huanxin.request;


import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.PathEnum;
import com.tinet.clink.huanxin.response.SessionHisResponse;

import java.util.List;

/**
 * 查询历史会话
 *
 * @author tian.jie
 * @date 2024-01-18 11:33
 */
public class SessionHisRequest extends AbstractRequestModel<SessionHisResponse> {

    /**
     * 查询页码索引,默认为1
     */
    private Integer page;

    /**
     * 每页显示的数据容量，默认为10，最大为50
     */
    private Integer per_page;


    /**
     * 渠道 不写为查询全部 要查询的话值为 网页:webim，APP：app，微信渠道weixin,微博为weibo，呼叫中心：phone
     */
    private String originType;

    /**
     * 客户昵称
     */
    private String customerName;

    /**
     * 是否转接 是：true 不是：false
     */
    private Boolean transfered;

    /**
     * 会话类型 回呼：true 呼入：false
     */
    private Boolean fromAgentCallback;

    /**
     * 评价 值为：0，1，2，3，4，5不填默认查询全部评价
     */
    private Integer enquirySummary;

    /**
     *“3 (一般)” 满意度评价（用于数字评价）
     */
    private Integer enquiryDegree;

    /**
     * “1” 解决率评价分数 1 已解决 2 未解决（用于数字评价）
     */
    private Integer resolutionScore;

    /**
     * “1” 解决率评价分数 1 已解决 2 未解决（用于数字评价）
     */
    private Integer resolutionScore2;

    /**
     * “1” 咨询结果: 有效会话 - 1 ; 无效会话 = 0
     */
    private Integer consultResult;

    /**
     * 多个技能组ID数组
     */
    private List<Integer> queueIds;

    /**
     * 会话接起时间(开始范围条件),时间格式 2018-06-16T23%3A59%3A59.000Z
     */
    private String beginDate;
    /**
     * 会话接起时间(结束范围条件),时间格式 2018-06-16T23%3A59%3A59.000Z
     */
    private String endDate;
    /**
     * 会话结束时间(开始范围条件),时间格式 2018-06-16T23%3A59%3A59.000Z
     */
    private String stopDateFrom;
    /**
     * 会话结束时间(结束范围条件),时间格式 2018-06-16T23%3A59%3A59.000Z
     */
    private String stopDateTo;
    /**
     * 会话创建时间(开始范围条件),时间格式 2018-06-16T23%3A59%3A59.000Z
     */
    private String createDateFrom;
    /**
     * 会话创建时间(结束范围条件),时间格式 2018-06-16T23%3A59%3A59.000Z
     */
    private String createDateTo;
    /**
     * 多个坐席的ID数组
     */
    private List<String> agentIds;

    /**
     * 多个坐席的ID数组
     */
    private String serviceSessionId;

    /**
     * 排序字段 stopDateTime startDateTime createDatetime
     */
    private String sortField;

    /**
     * 升降序asc desc
     */
    private String sortOrder;

    public SessionHisRequest() {
        super(PathEnum.QUERY_SESSION_HISTORYS.value(), HttpMethodType.POST);
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getTransfered() {
        return transfered;
    }

    public void setTransfered(Boolean transfered) {
        this.transfered = transfered;
    }

    public Boolean getFromAgentCallback() {
        return fromAgentCallback;
    }

    public void setFromAgentCallback(Boolean fromAgentCallback) {
        this.fromAgentCallback = fromAgentCallback;
    }

    public Integer getEnquirySummary() {
        return enquirySummary;
    }

    public void setEnquirySummary(Integer enquirySummary) {
        this.enquirySummary = enquirySummary;
    }

    public Integer getEnquiryDegree() {
        return enquiryDegree;
    }

    public void setEnquiryDegree(Integer enquiryDegree) {
        this.enquiryDegree = enquiryDegree;
    }

    public Integer getResolutionScore() {
        return resolutionScore;
    }

    public void setResolutionScore(Integer resolutionScore) {
        this.resolutionScore = resolutionScore;
    }

    public Integer getResolutionScore2() {
        return resolutionScore2;
    }

    public void setResolutionScore2(Integer resolutionScore2) {
        this.resolutionScore2 = resolutionScore2;
    }

    public Integer getConsultResult() {
        return consultResult;
    }

    public void setConsultResult(Integer consultResult) {
        this.consultResult = consultResult;
    }

    public List<Integer> getQueueIds() {
        return queueIds;
    }

    public void setQueueIds(List<Integer> queueIds) {
        this.queueIds = queueIds;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStopDateFrom() {
        return stopDateFrom;
    }

    public void setStopDateFrom(String stopDateFrom) {
        this.stopDateFrom = stopDateFrom;
    }

    public String getStopDateTo() {
        return stopDateTo;
    }

    public void setStopDateTo(String stopDateTo) {
        this.stopDateTo = stopDateTo;
    }

    public String getCreateDateFrom() {
        return createDateFrom;
    }

    public void setCreateDateFrom(String createDateFrom) {
        this.createDateFrom = createDateFrom;
    }

    public String getCreateDateTo() {
        return createDateTo;
    }

    public void setCreateDateTo(String createDateTo) {
        this.createDateTo = createDateTo;
    }

    public List<String> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<String> agentIds) {
        this.agentIds = agentIds;
    }

    public String getServiceSessionId() {
        return serviceSessionId;
    }

    public void setServiceSessionId(String serviceSessionId) {
        this.serviceSessionId = serviceSessionId;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public Class<SessionHisResponse> getResponseClass() {
        return SessionHisResponse.class;
    }
}
