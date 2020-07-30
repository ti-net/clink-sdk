package com.tinet.clink.openapi.model;

import java.util.Map;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatInvestigation {

    public Map getJoin() {
        return join;
    }

    public void setJoin(Map join) {
        this.join = join;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getKeys() {
        return keys;
    }

    public void setKeys(Integer keys) {
        this.keys = keys;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getLabel() {
        return label;
    }

    public void setLabel(String[] label) {
        this.label = label;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Short getHour() {
        return hour;
    }

    public void setHour(Short hour) {
        this.hour = hour;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getChatStartTime() {
        return chatStartTime;
    }

    public void setChatStartTime(Long chatStartTime) {
        this.chatStartTime = chatStartTime;
    }

    private Map join = null;

    private String mainUniqueId;                 // 主会话唯一标识
    private String uniqueId;                     // 会话唯一标识
    private Integer enterpriseId;                // 企业id
    private String visitorId;                   // visitor 访客id，此时用系统生成的uuid进行标识，关联唯一的访客
    private String visitorName;                 // 访客姓名
    private String cno;                         // 座席号
    private String clientName;                  // 座席姓名
    private String qno;                         // 队列号
    private String remark;                      // 满意度备注
    private Integer keys;                      // 满意度值
    private String description;                 // 满意度描述
    private String[] label;                       // 满意度标签
    private Long startTime;                      // 开始时间
    private Long endTime;                        // 结束时间
    private Integer totalDuration;              // 总时长（加工计算得出）

    private Short hour;                         // 小时

    /**
     * 队列名，用于满意度记录查询使用
     */
    private String qname;
    /**
     * 来源渠道
     */
    private Integer contactType;
    /**
     * 接入点
     */
    private String appId;
    /**
     * 接入点名字
     */
    private String appName;
    /**
     * 会话的开始时间
     */
    private Long chatStartTime;
}
