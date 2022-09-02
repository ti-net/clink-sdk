package com.tinet.clink.cc.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.ListCdrObsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询外呼通话记录列表请求
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class ListCdrObsRequest extends AbstractRequestModel<ListCdrObsResponse> {

    /**
     * 客户号码
     */
    private String customerNumber;

    /**
     * 座席号，要求只能是 4-6 位数字
     */
    private String cno;

    /**
     * 热线号码
     */
    private String hotline;

    /**
     * 接听状态。取值范围如下：
     * 0: 全部
     * 1: 客户未接听
     * 2: 座席未接听
     * 3: 双方接听
     * 默认值为 0
     */
    private Integer status;

    /**
     * 开始时间，时间戳格式精确到秒。默认值取当前月份第一天
     */
    private Long startTime;

    /**
     * 结束时间，时间戳格式精确到秒，开始时间和结束时间跨度不能超过一个月。默认值取当前时间
     */
    private Long endTime;

    /**
     * 是否隐藏号码。
     * 0: 不隐藏，1: 中间四位，2: 最后八位，3: 全部号码，4: 最后四位。默认值为 0
     */
    private Integer hiddenType;

    /**
     * 偏移量，范围 0-99990。默认值为 0，但limit + offset 不允许超过100000
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100。默认值为 10，但limit + offset 不允许超过100000
     */
    private Integer limit;

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


    public ListCdrObsRequest() {
        super(PathEnum.ListCdrObs.value(), HttpMethodType.GET);
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        if (customerNumber != null) {
            putQueryParameter("customerNumber", customerNumber);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putQueryParameter("cno", cno);
        }
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
        if (hotline != null) {
            putQueryParameter("hotline", hotline);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        if (status != null) {
            putQueryParameter("status", status);
        }
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
        }
    }

    public Integer getHiddenType() {
        return hiddenType;
    }

    public void setHiddenType(Integer hiddenType) {
        this.hiddenType = hiddenType;
        if (hiddenType != null) {
            putQueryParameter("hiddenType", hiddenType);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
        if (userField != null) {
            putQueryParameter("userField", userField);
        }
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
        if (mark != null) {
            putQueryParameter("mark", mark);
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        if (province != null) {
            putQueryParameter("province", province);
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        if (city != null) {
            putQueryParameter("city", city);
        }
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
        if (requestUniqueId != null) {
            putQueryParameter("requestUniqueId", requestUniqueId);
        }
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
        if (clientNumber != null) {
            putQueryParameter("clientNumber", clientNumber);
        }
    }

    public Integer getQueueAnswerInTime() {
        return queueAnswerInTime;
    }

    public void setQueueAnswerInTime(Integer queueAnswerInTime) {
        this.queueAnswerInTime = queueAnswerInTime;
        if (queueAnswerInTime != null) {
            putQueryParameter("queueAnswerInTime", queueAnswerInTime);
        }
    }


    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
        if (evaluation != null) {
            putQueryParameter("evaluation", evaluation);
        }
    }

    @Override
    public Class<ListCdrObsResponse> getResponseClass() {
        return ListCdrObsResponse.class;
    }
}

