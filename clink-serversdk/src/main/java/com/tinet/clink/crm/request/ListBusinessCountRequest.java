package com.tinet.clink.crm.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.response.ListBusinessCountResponse;
import com.tinet.clink.crm.response.ListBusinessResponse;

import java.util.Map;

/**
 * 业务记录查询
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
public class ListBusinessCountRequest extends AbstractRequestModel<ListBusinessCountResponse> {

    /**
     * 按创建时间查询的起始时间
     */
    private Long startTime;

    /**
     * 按创建时间查询的结束时间
     */
    private Long endTime;

    /**
     * 修改时间查询条件-开始时间
     */
    private Long updateStartTime;

    /**
     * 修改时间查询条件-结束时间
     */
    private Long updateEndTime;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 通话记录id
     */
    private String callId;

    /**
     * 在线会话ID
     */
    private String chatId;

    /**
     * 来源类型，0：呼叫中心 、1：在线咨询、2：微信、3：人工添加
     */
    private Integer source;

    /**
     * 客户姓名或电话
     */
    private String customerTel;

    /**
     * 客户电话
     */
    private String tel;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 自定义字段
     */
    private Map<String, String> customize;

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

    public Long getUpdateStartTime() {
        return updateStartTime;
    }

    public void setUpdateStartTime(Long updateStartTime) {
        this.updateStartTime = updateStartTime;
        if (updateStartTime != null) {
            putQueryParameter("updateStartTime", updateStartTime);
        }
    }

    public Long getUpdateEndTime() {
        return updateEndTime;
    }

    public void setUpdateEndTime(Long updateEndTime) {
        this.updateEndTime = updateEndTime;
        if (updateEndTime != null) {
            putQueryParameter("updateEndTime", updateEndTime);
        }
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<String, String> getCustomize() {
        return customize;
    }

    public void setCustomize(Map<String, String> customize) {
        this.customize = customize;
    }

    public ListBusinessCountRequest() {
        super(PathEnum.ListBusinessCount.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListBusinessCountResponse> getResponseClass() {
        return ListBusinessCountResponse.class;
    }
}