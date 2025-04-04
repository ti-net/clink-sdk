package com.tinet.clink.cc.request.cloudnumber;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cloudnumber.ListCloudNumberCdrsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * 查询云手机通话记录列表请求
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 **/
public class ListCloudNumberCdrsRequest extends AbstractRequestModel<ListCloudNumberCdrsResponse> {

    /**
     * 开始时间，单位：s
     */
    private Long startTime;

    /**
     * 结束时间，单位：s
     */
    private Long endTime;
    /**
     * 呼叫类型：ib-呼入、ob-外呼、all 或 不设置 - 所有（默认）
     */
    private String type;
    /**
     * 话单id
     */
    private String uniqueId;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 云号码模式：1-实体卡，2-虚拟工作卡，3-两端呼，4-RTC
     */
    private Integer cloudNumberMode;

    /**
     * 接听状态：0-所有（默认）、1-接听、2-未接听；
     */
    private Integer bridgeStatus;
    /**
     * 客户号码
     */
    private String customerNumber;
    /**
     * 外显号码
     */
    private String clid;

    /**
     * 是否隐藏号码 0 不隐藏，1隐藏中间四位 2隐藏最后八位 3隐藏全部号码 4隐藏最后四位 默认是0
     */
    private Integer hiddenType = 0;


    public ListCloudNumberCdrsRequest() {
        super(PathEnum.ListCloudNumberCdrs.value(), HttpMethodType.GET);
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (Objects.nonNull(startTime)) {
            putQueryParameter("startTime", startTime);
        }
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (Objects.nonNull(endTime)) {
            putQueryParameter("endTime", endTime);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if (Objects.nonNull(type)) {
            putQueryParameter("type", type);
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (Objects.nonNull(uniqueId)) {
            putQueryParameter("uniqueId", uniqueId);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            putQueryParameter("cno", cno);
        }
    }

    public Integer getCloudNumberMode() {
        return cloudNumberMode;
    }

    public void setCloudNumberMode(Integer cloudNumberMode) {
        this.cloudNumberMode = cloudNumberMode;
        if (Objects.nonNull(cloudNumberMode)) {
            putQueryParameter("cloudNumberMode", cloudNumberMode);
        }
    }

    public Integer getBridgeStatus() {
        return bridgeStatus;
    }

    public void setBridgeStatus(Integer bridgeStatus) {
        this.bridgeStatus = bridgeStatus;
        if (Objects.nonNull(bridgeStatus)) {
            putQueryParameter("bridgeStatus", bridgeStatus);
        }
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        if (Objects.nonNull(customerNumber)) {
            putQueryParameter("customerNumber", customerNumber);
        }
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
        if (Objects.nonNull(clid)) {
            putQueryParameter("clid", clid);
        }
    }

    public Integer getHiddenType() {
        return hiddenType;
    }

    public void setHiddenType(Integer hiddenType) {
        this.hiddenType = hiddenType;
        if (Objects.nonNull(hiddenType)) {
            putQueryParameter("hiddenType", hiddenType);
        }
    }


    @Override
    public Class<ListCloudNumberCdrsResponse> getResponseClass() {
        return ListCloudNumberCdrsResponse.class;
    }
}

