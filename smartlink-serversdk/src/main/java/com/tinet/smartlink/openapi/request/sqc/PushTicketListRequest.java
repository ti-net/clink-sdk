package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.model.sqc.OriginData;
import com.tinet.smartlink.openapi.model.sqc.TicketRecord;
import com.tinet.smartlink.openapi.response.sqc.PushTicketListResponse;

import java.util.List;

/**
 * 工单质检推送请求实体
 *
 * @author wnagdabao
 * @date 2019/8/13 9:50
 */
public class PushTicketListRequest extends BaseRequest<PushTicketListResponse> {
    /**
     * 账号
     */
    private String accountLoginName;
    /**
     * 企业id
     */
    private String userId;
    /**
     * 第一个工单节点的创建时间
     */
    private Long createTime;
    /**
     * 是否是结束结点，0 为流转结点 1 为结束结点
     */
    private Integer finished;
    /**
     * 优先级 0: 低, 1: 中, 2: 高, 3: 紧急
     */
    private Integer priority;
    /**
     * 平台推送的工单id
     */
    private String ticketId;
    /**
     * 工单节点内容
     */
    private List<TicketRecord> ticketRecord;

    /**
     * 自定义字段传值list
     */
    private List<OriginData> originDataList;


    public List<OriginData> getOriginDataList() {
        return originDataList;
    }

    public void setOriginDataList(List<OriginData> originDataList) {
        this.originDataList = originDataList;
        if (originDataList != null) {
            putBodyParameter("originDataList", originDataList);
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

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
        if (accountLoginName != null) {
            putBodyParameter("accountLoginName", accountLoginName);
        }
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
        if (createTime != null) {
            putBodyParameter("createTime", createTime);
        }
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
        if (finished != null) {
            putBodyParameter("finished", finished);
        }
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
        if (priority != null) {
            putBodyParameter("priority", priority);
        }
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
        if (ticketId != null) {
            putBodyParameter("ticketId", ticketId);
        }
    }

    public List<TicketRecord> getTicketRecord() {
        return ticketRecord;
    }

    public void setTicketRecord(List<TicketRecord> ticketRecord) {
        this.ticketRecord = ticketRecord;
        if (ticketRecord != null) {
            putBodyParameter("ticketRecord", ticketRecord);
        }
    }

    public PushTicketListRequest() {
        super("/sqc/pushTicketList", HttpMethodType.POST);
    }

    @Override
    public Class<PushTicketListResponse> getResponseClass() {
        return PushTicketListResponse.class;
    }
}
