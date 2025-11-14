package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.ListTicketResponse;

import java.util.Map;

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
     * 自定义工单编号
     */
    private String customId;

    /**
     * 客户资料ID
     */
    private Integer customerId;

    /**
     * 处理状态 0: 待领取 2: 处理中 4: 已完成 5: 已关闭 9: 待接单。 默认为全部状态
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
     * 工单模板 ID, 查询所处任务节点的工单时, 工单模板 ID必传
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
     * 工单优先级 0: 低, 1: 中, 2: 高, 3: 紧急 (系统默认优先级 可自定义)
     */
    private Integer level;

    /**
     * 工单标签名称
     */
    private String tag;

    /**
     * 工单类别 1: 预制工作流模板, 2: 人工分配模板 不传为所有类别
     */
    private Integer type;

    /**
     * 开始时间 按照格式 "yyyy-MM-dd HH:mm:ss" 进行格式化
     */
    private String startTime;

    /**
     * 结束时间 按照格式 "yyyy-MM-dd HH:mm:ss" 进行格式化
     */
    private String endTime;

    /**
     * 工单完成开始时间 按照格式 "yyyy-MM-dd HH:mm:ss" 进行格式化
     */
    private String endTimeStart;

    /**
     * 工单完成结束时间 按照格式 "yyyy-MM-dd HH:mm:ss" 进行格式化
     */
    private String endTimeEnd;

    /**
     * 工单关闭开始时间 按照格式 "yyyy-MM-dd HH:mm:ss" 进行格式化
     */
    private String closeTimeStart;

    /**
     * 工单关闭结束时间 按照格式 "yyyy-MM-dd HH:mm:ss" 进行格式化
     */
    private String closeTimeEnd;

    /**
     * 更新开始时间 按照格式 "yyyy-MM-dd HH:mm:ss" 进行格式化
     */
    private String updateTimeStart;

    /**
     * 更新结束时间 按照格式 "yyyy-MM-dd HH:mm:ss" 进行格式化
     */
    private String updateTimeEnd;

    /**
     * 最近催单开始时间 按照格式 "yyyy-MM-dd" 进行格式化
     */
    private String lastReminderTimeStart;

    /**
     * 最近催单结束时间 按照格式 "yyyy-MM-dd" 进行格式化
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

    /**
     * 节点唯一标识
     */
    private String taskKey;

    /**
     * 自定义字段集合
     */
    private Map<String, String> fields;

    /**
     * 自定义系统字段集合
     */
    private Map<String, String> systemFields;

    /**
     * 来源 0: 呼叫中心, 1: 在线咨询, 2: 微信, 3: 人工添加, 4: 小程序, 5: 微博, 6: 电子邮件, 7: app端, 8: 企业微信, 9: 微信客服（独立版）, 10: 第三方接入, 11: 接口创建, 12: 企微客服, 14: 抖音企业号, 15: 百度营销, 16: sdk渠道, 17: 自助工单, 18: 访客端APP, 19: 微信客服, 20: 人工导入创建, 21: 企微助手, 22: 自动化任务创建, 23:飞书, 24: 留言节点, 30: 接口创建, 31: 知识库审批, 32: 智能质检, 33: 触发器, 34: 视频客服, 35: 邮件工单, 36: 在线留言, 37: 机器人工单, 38: 小红书
     */
    private Integer source;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
        if (customId != null) {
            putQueryParameter("customId", customId);
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

    public String getEndTimeStart() {
        return endTimeStart;
    }

    public void setEndTimeStart(String endTimeStart) {
        this.endTimeStart = endTimeStart;
        if (endTimeStart != null) {
            putQueryParameter("endTimeStart",endTimeStart);
        }
    }

    public String getEndTimeEnd() {
        return endTimeEnd;
    }

    public void setEndTimeEnd(String endTimeEnd) {
        this.endTimeEnd = endTimeEnd;
        if (endTimeEnd != null) {
            putQueryParameter("endTimeEnd",endTimeEnd);
        }
    }

    public String getCloseTimeStart() {
        return closeTimeStart;
    }

    public void setCloseTimeStart(String closeTimeStart) {
        this.closeTimeStart = closeTimeStart;
        if (closeTimeStart != null) {
            putQueryParameter("closeTimeStart",closeTimeStart);
        }
    }

    public String getCloseTimeEnd() {
        return closeTimeEnd;

    }

    public void setCloseTimeEnd(String closeTimeEnd) {
        this.closeTimeEnd = closeTimeEnd;
        if (closeTimeEnd != null) {
            putQueryParameter("closeTimeEnd",closeTimeEnd);
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

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
        if (taskKey != null) {
            putQueryParameter("taskKey", taskKey);
        }
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
        if (fields != null) {
            putQueryParameter("fields", fields);
        }
    }

    public Map<String, String> getSystemFields() {
        return systemFields;
    }

    public void setSystemFields(Map<String, String> systemFields) {
        this.systemFields = systemFields;
        if (systemFields != null) {
            putQueryParameter("systemFields", systemFields);
        }
    }


    public void setUpdateTimeStart(String updateTimeStart) {
        this.updateTimeStart = updateTimeStart;
        if (updateTimeStart != null) {
            putQueryParameter("updateTimeStart", updateTimeStart);
        }
    }

    public String getUpdateTimeStart() {
        return updateTimeStart;
    }

    public void setUpdateTimeEnd(String updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
        if (updateTimeEnd != null) {
            putQueryParameter("updateTimeEnd", updateTimeEnd);
        }
    }

    public String getupdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setSource(Integer source) {
        this.source = source;
        if (source != null) {
            putQueryParameter("source", source);
        }
    }

    public Integer getSource() {
        return source;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
        if (customerId != null) {
            putQueryParameter("customerId", customerId);
        }
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public ListTicketRequest() {
        super(PathEnum.ListTicket.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListTicketResponse> getResponseClass() {
        return ListTicketResponse.class;
    }

}