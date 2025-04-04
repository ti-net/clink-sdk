package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.UserIdType;
import com.tinet.clink.ticket.response.TicketAssignResponse;

/**
 * @description 类说明
 * @Author ChenZM
 * @Date 2023/05/25
 */
public class TicketAssignRequest extends AbstractRequestModel<TicketAssignResponse> {
    public TicketAssignRequest() {
        super(PathEnum.AssignTicket.value(), HttpMethodType.POST);
    }

    /**
     * 操作人ID
     */
    private Integer operatorId;
    /**
     * 类型
     */
    private Integer operatorIdType = UserIdType.USER_ID.getCode();

    /**
     * 工单ID
     */
    private Integer id;

    /**
     * 处理人类型
     */
    private Integer handlerType;

    /**
     * 处理人ID
     */
    private Integer handlerId;

    /**
     * 处理人ID
     */
    private Integer handlerIdType = UserIdType.USER_ID.getCode();

    /**
     * 是否开启自动分配
     */
    private Integer autoAssign;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 评论内容
     */
    private String content;

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
        if (operatorId != null) {
            putQueryParameter("operatorId",operatorId);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id",id);
        }
    }

    public Integer getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(Integer handlerType) {
        this.handlerType = handlerType;
        if (handlerType != null) {
            putQueryParameter("handlerType",handlerType);
        }
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
        if (handlerId != null) {
            putQueryParameter("handlerId",handlerId);
        }
    }

    public Integer getAutoAssign() {
        return autoAssign;
    }

    public void setAutoAssign(Integer autoAssign) {
        this.autoAssign = autoAssign;
        if (autoAssign != null) {
            putQueryParameter("autoAssign",autoAssign);
        }
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
        if (taskId != null) {
            putQueryParameter("taskId",taskId);
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        if (content != null) {
            putQueryParameter("content",content);
        }
    }

    public Integer getHandlerIdType() {
        return handlerIdType;
    }

    public void setHandlerIdType(Integer handlerIdType) {
        this.handlerIdType = handlerIdType;
        if (handlerIdType != null) {
            putQueryParameter("handlerIdType",handlerIdType);
        }
    }

    public Integer getOperatorIdType() {
        return operatorIdType;
    }

    public void setOperatorIdType(Integer operatorIdType) {
        this.operatorIdType = operatorIdType;
        if (operatorIdType != null) {
            putQueryParameter("operatorIdType",operatorIdType);
        }
    }

    @Override
    public Class<TicketAssignResponse> getResponseClass() {
        return TicketAssignResponse.class;
    }
}
