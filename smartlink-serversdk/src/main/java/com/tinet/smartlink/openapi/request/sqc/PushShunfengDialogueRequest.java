package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.model.sqc.ChatRecord;
import com.tinet.smartlink.openapi.model.sqc.OriginData;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushShunfengDialogueResponse;

import java.util.List;

/**
 * 顺丰专项记录质检推送请求实体
 *
 * @author chenjg
 * @date 2020/09/02
 */
public class PushShunfengDialogueRequest extends BaseRequest<PushShunfengDialogueResponse> {

    /**
     * 唯一id
     */
    private String uniqueId;
    /**
     * 账号登录名称
     */
    private String accountLoginName;

    /**
     * 用户id 内部对应着企业id
     */
    private String userId;

    /**
     * 访客ip归属省份
     */
    private String customerProvince;
    /**
     * 访客ip归属城市
     */
    private String customerCity;
    /**
     * 访客ip地址
     */
    private String customerIp;

    /**
     * 访客设备标识
     */
    private String browserUserAgent;
    /**
     * 客服id
     */
    private String cno;
    /**
     * 客服登录名称
     */
    private String agentName;
    /**
     * 队列号
     */
    private String qno;
    /**
     * 队列名称
     */
    private String qname;
    /**
     * 进入系统时间
     */
    private Long startTime;
    /**
     * 转人工时间
     */
    private Long sendChatTime;
    /**
     * 接通人工时间
     */
    private Long chatBeginTime;
    /**
     * 人工对话结束时间
     */
    private Long chatEndTime;
    /**
     * 人工对话结束时间
     */
    private Long endTime;
    /**
     * 机器人会话结束时间
     */
    private Long robotEndTime;
    /**
     * 访客退出原因
     */
    private String endReason;
    /**
     * 满意度调查结果
     */
    private String investigation;
    /**
     * 客户号码
     */
    private String customerNumber;

    /**
     * 对话特征
     */
    private String conversationFeature;

    /**
     * 座席聊天内容
     */
    private List<ChatRecord> chatAgent;
    /**
     * 客户聊天内容
     */
    private List<ChatRecord> chatCustomer;

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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putBodyParameter("uniqueId", uniqueId);
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
        if (customerProvince != null) {
            putBodyParameter("customerProvince", customerProvince);
        }
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
        if (customerCity != null) {
            putBodyParameter("customerCity", customerCity);
        }
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
        if (customerIp != null) {
            putBodyParameter("customerIp", customerIp);
        }
    }

    public String getBrowserUserAgent() {
        return browserUserAgent;
    }

    public void setBrowserUserAgent(String browserUserAgent) {
        this.browserUserAgent = browserUserAgent;
        if (browserUserAgent != null) {
            putBodyParameter("browserUserAgent", browserUserAgent);
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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putBodyParameter("startTime", startTime);
        }
    }

    public Long getSendChatTime() {
        return sendChatTime;
    }

    public void setSendChatTime(Long sendChatTime) {
        this.sendChatTime = sendChatTime;
        if (sendChatTime != null) {
            putBodyParameter("sendChatTime", sendChatTime);
        }
    }

    public Long getChatBeginTime() {
        return chatBeginTime;
    }

    public void setChatBeginTime(Long chatBeginTime) {
        this.chatBeginTime = chatBeginTime;
        if (chatBeginTime != null) {
            putBodyParameter("chatBeginTime", chatBeginTime);
        }
    }

    public Long getChatEndTime() {
        return chatEndTime;
    }

    public void setChatEndTime(Long chatEndTime) {
        this.chatEndTime = chatEndTime;
        if (chatEndTime != null) {
            putBodyParameter("chatEndTime", chatEndTime);
        }
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putBodyParameter("endTime", endTime);
        }
    }

    public Long getRobotEndTime() {
        return robotEndTime;
    }

    public void setRobotEndTime(Long robotEndTime) {
        this.robotEndTime = robotEndTime;
        if (robotEndTime != null) {
            putBodyParameter("robotEndTime", robotEndTime);
        }
    }

    public String getEndReason() {
        return endReason;
    }

    public void setEndReason(String endReason) {
        this.endReason = endReason;
        if (endReason != null) {
            putBodyParameter("endReason", endReason);
        }
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
        if (investigation != null) {
            putBodyParameter("investigation", investigation);
        }
    }

    public List<ChatRecord> getChatAgent() {
        return chatAgent;
    }

    public void setChatAgent(List<ChatRecord> chatAgent) {
        this.chatAgent = chatAgent;
        if (chatAgent != null) {
            putBodyParameter("chatAgent", chatAgent);
        }
    }

    public List<ChatRecord> getChatCustomer() {
        return chatCustomer;
    }

    public void setChatCustomer(List<ChatRecord> chatCustomer) {
        this.chatCustomer = chatCustomer;
        if (chatCustomer != null) {
            putBodyParameter("chatCustomer", chatCustomer);
        }
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getConversationFeature() {
        return conversationFeature;
    }

    public void setConversationFeature(String conversationFeature) {
        this.conversationFeature = conversationFeature;
    }

    public PushShunfengDialogueRequest() {
        super("/sqc/pushShunfengDialogue", HttpMethodType.POST);
    }

    @Override
    public Class<PushShunfengDialogueResponse> getResponseClass() {
        return PushShunfengDialogueResponse.class;
    }
}
