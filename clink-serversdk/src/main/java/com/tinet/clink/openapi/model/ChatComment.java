package com.tinet.clink.openapi.model;

/**
 * 会话留言记录索引类
 */
public class ChatComment {

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
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

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getIvrNodeId() {
        return ivrNodeId;
    }

    public void setIvrNodeId(Integer ivrNodeId) {
        this.ivrNodeId = ivrNodeId;
    }

    public String getIvrNodeName() {
        return ivrNodeName;
    }

    public void setIvrNodeName(String ivrNodeName) {
        this.ivrNodeName = ivrNodeName;
    }

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 会话唯一标识
     */
    private String uniqueId;

    /**
     * 主会话唯一标识
     */
    private String mainUniqueId;

    /**
     * visitor 访客id，此时用系统生成的uuid进行标识，关联唯一的访客
     */
    private String visitorId;

    /**
     * 访客姓名
     */
    private String visitorName;

    /**
     * 会话类型
     */
    private Integer contactType;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 队列号
     */
    private String qno;

    /**
     * 来源渠道，网址url或者公众号
     */
    private String appName;

    /**
     * 留言节点id
     */
    private Integer ivrNodeId;

    /**
     * 留言节点名称
     */
    private String ivrNodeName;
}

