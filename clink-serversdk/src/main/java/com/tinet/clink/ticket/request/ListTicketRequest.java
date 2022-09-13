package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.ListTicketResponse;

/**
 * 工单记录查询
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
public class ListTicketRequest extends AbstractRequestModel<ListTicketResponse> {


    /**
     * 工单id
     */
    private Integer id;

    /**
     * 工单的处理状态 不传为状态 0：待领取 2：处理中 3：已撤销 4：已完成
     */
    private Integer handleStatus;

    /**
     * 工单创建人id
     */
    private Integer creatorId;

    /**
     * 工单主题
     */
    private String topic;

    /**
     * 工单模板 Id
     */
    private Integer workflowId;

    /**
     * 处理类型 0:座席 1:队列
     */
    private Integer handlerType;

    /**
     * 处理人id
     */
    private Integer handlerId;

    /**
     * 优先级 不传为所有优先级 0：低、1：中、2：高、3：紧急
     */
    private Integer level;

    /**
     * 工单标签名称
     */
    private String tag;

    /**
     * 工单类别 不传为所有类别 1：预制工作流模板 2：人工分配模板
     */
    private Integer type;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 最近催单开始时间
     */
    private String lastReminderTimeStart;

    /**
     * 最近催单结束时间
     */
    private String lastReminderTimeEnd;

    /**
     * 催单次数
     */
    private Integer reminderCount;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户名称
     */
    private String customerTel;

    /**
     * 偏移量，范围 0-10000，默认值为 0
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100，默认值为 10
     */
    private Integer limit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
        if (handleStatus != null) {
            putQueryParameter("handleStatus", handleStatus);
        }
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
        if (creatorId != null) {
            putQueryParameter("creatorId", creatorId);
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
        if (topic != null) {
            putQueryParameter("topic", topic);
        }
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
        if (workflowId != null) {
            putQueryParameter("workflowId", workflowId);
        }
    }

    public Integer getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(Integer handlerType) {
        this.handlerType = handlerType;
        if (handlerType != null) {
            putQueryParameter("handlerType", handlerType);
        }
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
        if (handlerId != null) {
            putQueryParameter("handlerId", handlerId);
        }
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        if (level != null) {
            putQueryParameter("level", level);
        }
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
        if (tag != null) {
            putQueryParameter("tag", tag);
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            putQueryParameter("type", type);
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
        }
    }

    public String getLastReminderTimeStart() {
        return lastReminderTimeStart;
    }

    public void setLastReminderTimeStart(String lastReminderTimeStart) {
        this.lastReminderTimeStart = lastReminderTimeStart;
        if (lastReminderTimeStart != null) {
            putQueryParameter("lastReminderTimeStart", lastReminderTimeStart);
        }
    }

    public String getLastReminderTimeEnd() {
        return lastReminderTimeEnd;
    }

    public void setLastReminderTimeEnd(String lastReminderTimeEnd) {
        this.lastReminderTimeEnd = lastReminderTimeEnd;
        if (lastReminderTimeEnd != null) {
            putQueryParameter("lastReminderTimeEnd", lastReminderTimeEnd);
        }
    }

    public Integer getReminderCount() {
        return reminderCount;
    }

    public void setReminderCount(Integer reminderCount) {
        this.reminderCount = reminderCount;
        if (reminderCount != null) {
            putQueryParameter("reminderCount", reminderCount);
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
        if (customerName != null) {
            putQueryParameter("customerName", customerName);
        }
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
        if (customerTel != null) {
            putQueryParameter("customerTel", customerTel);
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

    public ListTicketRequest() {
        super(PathEnum.ListTicket.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListTicketResponse> getResponseClass() {
        return ListTicketResponse.class;
    }

}