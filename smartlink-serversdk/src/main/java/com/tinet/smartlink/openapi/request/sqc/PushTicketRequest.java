package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushTicketResponse;

/**
 * 工单质检推送请求实体
 *
 * @author wnagdabao
 * @date 2019/8/13 9:50
 */
public class PushTicketRequest extends BaseRequest<PushTicketResponse> {
    
    private String accountLoginName;
    
    private String userId;
    
    /**
     * 工单 ID
     */
    private String ticketId;
    /**
     * 工单 ID
     */
    private String taskId;
    
    private String comment;
    
    /**
     * 是否是结束结点，1 为结束结点
     */
    private Integer finished;
    
    private String cno;
    
    private String agentName;
    
    private String qno;
    
    private String qname;
    
    private Long createTime;
    
    private Long updateTime;

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
        if (accountLoginName != null) {
            putBodyParameter("accountLoginName", accountLoginName);
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

    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
        if (comment != null) {
            putBodyParameter("comment", comment);
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
    
    public String getTaskId() {
        return taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
        if (taskId != null) {
            putBodyParameter("taskId", taskId);
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

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
        if (agentName != null) {
            putBodyParameter("agentName", agentName);
        }
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
        if (qno != null) {
            putBodyParameter("qno", qno);
        }
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
        if (qname != null) {
            putBodyParameter("qname", qname);
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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        if (updateTime != null) {
            putBodyParameter("updateTime", updateTime);
        }
    }

    public PushTicketRequest() {
        super("/sqc/pushTicket", HttpMethodType.POST);
    }

    @Override
    public Class<PushTicketResponse> getResponseClass() {
        return PushTicketResponse.class;
    }
}
