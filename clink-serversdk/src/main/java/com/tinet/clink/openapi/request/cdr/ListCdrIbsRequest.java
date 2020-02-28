package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.ListCdrIbsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询呼入通话记录列表请求
 *
 * @author huwk
 * @date 2018/10/23
 **/
public class ListCdrIbsRequest extends AbstractRequestModel<ListCdrIbsResponse> {

    /**
     * 队列号，要求只能是 4-6 位数字
     */
    private String qno;

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
     * 1: 座席接听
     * 2: 已呼叫座席，座席未接听
     * 3: 系统接听
     * 4: 系统未接听-IVR配置错误
     * 5: 系统未接听-停机
     * 6: 系统未接听-欠费
     * 7: 系统未接听-黑名单
     * 8: 系统未接听-未注册
     * 9: 系统未接听-彩铃
     * 10: 系统未接听-网上400
     * 11: 系统未接听-呼叫超出营帐中设置的最大限制
     * 12: 系统未接听-客户呼入系统后在系统未应答前挂机
     * 13: 其他错误
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
     * 偏移量，范围 0-10000。默认值为 0
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100。默认值为 10
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


    public ListCdrIbsRequest() {
        super(PathEnum.ListCdrIbs.value(), HttpMethodType.GET);
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
        if (qno != null) {
            putQueryParameter("qno", qno);
        }
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

    @Override
    public Class<ListCdrIbsResponse> getResponseClass() {
        return ListCdrIbsResponse.class;
    }
}
