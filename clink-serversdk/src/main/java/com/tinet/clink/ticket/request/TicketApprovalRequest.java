package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.TicketApprovalResponse;

/**
 * 工单审批
 *
 * @author auto-generated
 * @date 2024
 */
public class TicketApprovalRequest extends AbstractRequestModel<TicketApprovalResponse> {

    /**
     * 任务ID（必传）
     */
    private String taskId;

    /**
     * 工单ID（可选，与externalId必须二选一）
     */
    private Integer id;

    /**
     * 工单外部ID（可选，与id必须二选一）
     */
    private String externalId;

    private Integer approverIdType;

    /**
     * 审批人ID（必传）
     */
    private String approverId;

    /**
     * 审批结果（必传）：0-同意，1-拒绝
     */
    private Integer approvalResult;

    /**
     * 审批意见（可选）
     */
    private String approvalComment;

    public TicketApprovalRequest() {
        super(PathEnum.ApproveTicket.value(), HttpMethodType.POST);
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public Integer getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(Integer approvalResult) {
        this.approvalResult = approvalResult;
    }

    public String getApprovalComment() {
        return approvalComment;
    }

    public void setApprovalComment(String approvalComment) {
        this.approvalComment = approvalComment;
    }

    @Override
    public Class<TicketApprovalResponse> getResponseClass() {
        return TicketApprovalResponse.class;
    }

    public Integer getApproverIdType() {
        return approverIdType;
    }

    public void setApproverIdType(Integer approverIdType) {
        this.approverIdType = approverIdType;
    }
}
