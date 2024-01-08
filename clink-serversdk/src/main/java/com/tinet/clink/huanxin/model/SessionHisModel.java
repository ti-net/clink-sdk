package com.tinet.clink.huanxin.model;



import java.util.List;

public class SessionHisModel {

    private Integer total_entries;

    private List<HisItem> items;

    public Integer getTotal_entries() {
        return total_entries;
    }

    public void setTotal_entries(Integer total_entries) {
        this.total_entries = total_entries;
    }

    public List<HisItem> getItems() {
        return items;
    }

    public void setItems(List<HisItem> items) {
        this.items = items;
    }

    public static class HisItem {

        private String serviceSessionId;//	会话ID
        private Integer tenantId	;//	租户ID
        private Integer techChannelId	;//	关联ID
        private Integer queueId	;//	技能组ID
        private String state	;//	会话状态
        private Integer chatGroupId	;//	访客的群组ID
        private Integer messageSeqId	;//	最大消息序列自增标记
        private String agentUserId	;//	坐席ID
        private String agentUserNiceName	;//	坐席昵称
        private String agentUserTrueName	;//	真实姓名
        private String agentUserType	;//	坐席类型
        private String createDatetime	;//	会话创建时间
        private String startDateTime	;//	会话接起时间
        private String agentLastMessageDate	;//	坐席最后一条消息的时间
        private String stopDateTime	;//	会话结束时间
        private String techChannelType	;//	关联类型
        private List<List<Summary>> summarys;

        private String enquirySummary	;//		客户满意度评价
        private String enquiryDetail	;//		客户满意度评价详情
        private String techChannelName	;//		关联名称
        private List<String> originType	;//		渠道类型 网页:webim，APP：app，微信渠道weixin,微博为weibo，呼叫中心：phone
        private String comment	;//		会话备注

        private VisitorUser visitorUser;
        private Customer customer;

        private String messageDetail	;//		消息详情
        private String recordFileUrl	;//		话单录音文件名
        private Boolean fromAgentCallback	;//		会话类型 回呼：true 呼入：false
        private Boolean  transfered	;//		是否转接 是：true 不是：false
        private String visitorUserTags	;//		访客标签
        private String enquiryTags	;//		满意度评价标签
        private String chatMessages	;//		聊天消息
        private String lastChatMessage	;//		最后一条消息

        private List<AgentUser> agentUserSet;

        private List<AgentQueue> agentQueueSet;

        private String enquiryTagNames	;//		满意度评价标签的名字
        private String summarysDetail	;//		会话小结文本内容

        private List<ServiceSessionEventHistory> serviceSessionEventHistoryList;

        private ServiceSessionAttribute serviceSessionAttribute;

        public String getServiceSessionId() {
            return serviceSessionId;
        }

        public void setServiceSessionId(String serviceSessionId) {
            this.serviceSessionId = serviceSessionId;
        }

        public Integer getTenantId() {
            return tenantId;
        }

        public void setTenantId(Integer tenantId) {
            this.tenantId = tenantId;
        }

        public Integer getTechChannelId() {
            return techChannelId;
        }

        public void setTechChannelId(Integer techChannelId) {
            this.techChannelId = techChannelId;
        }

        public Integer getQueueId() {
            return queueId;
        }

        public void setQueueId(Integer queueId) {
            this.queueId = queueId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Integer getChatGroupId() {
            return chatGroupId;
        }

        public void setChatGroupId(Integer chatGroupId) {
            this.chatGroupId = chatGroupId;
        }

        public Integer getMessageSeqId() {
            return messageSeqId;
        }

        public void setMessageSeqId(Integer messageSeqId) {
            this.messageSeqId = messageSeqId;
        }

        public String getAgentUserId() {
            return agentUserId;
        }

        public void setAgentUserId(String agentUserId) {
            this.agentUserId = agentUserId;
        }

        public String getAgentUserNiceName() {
            return agentUserNiceName;
        }

        public void setAgentUserNiceName(String agentUserNiceName) {
            this.agentUserNiceName = agentUserNiceName;
        }

        public String getAgentUserTrueName() {
            return agentUserTrueName;
        }

        public void setAgentUserTrueName(String agentUserTrueName) {
            this.agentUserTrueName = agentUserTrueName;
        }

        public String getAgentUserType() {
            return agentUserType;
        }

        public void setAgentUserType(String agentUserType) {
            this.agentUserType = agentUserType;
        }

        public String getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(String createDatetime) {
            this.createDatetime = createDatetime;
        }

        public String getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(String startDateTime) {
            this.startDateTime = startDateTime;
        }

        public String getAgentLastMessageDate() {
            return agentLastMessageDate;
        }

        public void setAgentLastMessageDate(String agentLastMessageDate) {
            this.agentLastMessageDate = agentLastMessageDate;
        }

        public String getStopDateTime() {
            return stopDateTime;
        }

        public void setStopDateTime(String stopDateTime) {
            this.stopDateTime = stopDateTime;
        }

        public String getTechChannelType() {
            return techChannelType;
        }

        public void setTechChannelType(String techChannelType) {
            this.techChannelType = techChannelType;
        }

        public List<List<Summary>> getSummarys() {
            return summarys;
        }

        public void setSummarys(List<List<Summary>> summarys) {
            this.summarys = summarys;
        }

        public String getEnquirySummary() {
            return enquirySummary;
        }

        public void setEnquirySummary(String enquirySummary) {
            this.enquirySummary = enquirySummary;
        }

        public String getEnquiryDetail() {
            return enquiryDetail;
        }

        public void setEnquiryDetail(String enquiryDetail) {
            this.enquiryDetail = enquiryDetail;
        }

        public String getTechChannelName() {
            return techChannelName;
        }

        public void setTechChannelName(String techChannelName) {
            this.techChannelName = techChannelName;
        }

        public List<String> getOriginType() {
            return originType;
        }

        public void setOriginType(List<String> originType) {
            this.originType = originType;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public VisitorUser getVisitorUser() {
            return visitorUser;
        }

        public void setVisitorUser(VisitorUser visitorUser) {
            this.visitorUser = visitorUser;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public String getMessageDetail() {
            return messageDetail;
        }

        public void setMessageDetail(String messageDetail) {
            this.messageDetail = messageDetail;
        }

        public String getRecordFileUrl() {
            return recordFileUrl;
        }

        public void setRecordFileUrl(String recordFileUrl) {
            this.recordFileUrl = recordFileUrl;
        }

        public Boolean getFromAgentCallback() {
            return fromAgentCallback;
        }

        public void setFromAgentCallback(Boolean fromAgentCallback) {
            this.fromAgentCallback = fromAgentCallback;
        }

        public Boolean getTransfered() {
            return transfered;
        }

        public void setTransfered(Boolean transfered) {
            this.transfered = transfered;
        }

        public String getVisitorUserTags() {
            return visitorUserTags;
        }

        public void setVisitorUserTags(String visitorUserTags) {
            this.visitorUserTags = visitorUserTags;
        }

        public String getEnquiryTags() {
            return enquiryTags;
        }

        public void setEnquiryTags(String enquiryTags) {
            this.enquiryTags = enquiryTags;
        }

        public String getChatMessages() {
            return chatMessages;
        }

        public void setChatMessages(String chatMessages) {
            this.chatMessages = chatMessages;
        }

        public String getLastChatMessage() {
            return lastChatMessage;
        }

        public void setLastChatMessage(String lastChatMessage) {
            this.lastChatMessage = lastChatMessage;
        }

        public List<AgentUser> getAgentUserSet() {
            return agentUserSet;
        }

        public void setAgentUserSet(List<AgentUser> agentUserSet) {
            this.agentUserSet = agentUserSet;
        }

        public List<AgentQueue> getAgentQueueSet() {
            return agentQueueSet;
        }

        public void setAgentQueueSet(List<AgentQueue> agentQueueSet) {
            this.agentQueueSet = agentQueueSet;
        }

        public String getEnquiryTagNames() {
            return enquiryTagNames;
        }

        public void setEnquiryTagNames(String enquiryTagNames) {
            this.enquiryTagNames = enquiryTagNames;
        }

        public String getSummarysDetail() {
            return summarysDetail;
        }

        public void setSummarysDetail(String summarysDetail) {
            this.summarysDetail = summarysDetail;
        }

        public List<ServiceSessionEventHistory> getServiceSessionEventHistoryList() {
            return serviceSessionEventHistoryList;
        }

        public void setServiceSessionEventHistoryList(List<ServiceSessionEventHistory> serviceSessionEventHistoryList) {
            this.serviceSessionEventHistoryList = serviceSessionEventHistoryList;
        }

        public ServiceSessionAttribute getServiceSessionAttribute() {
            return serviceSessionAttribute;
        }

        public void setServiceSessionAttribute(ServiceSessionAttribute serviceSessionAttribute) {
            this.serviceSessionAttribute = serviceSessionAttribute;
        }
    }


    public static class ServiceSessionAttribute {

        private String carrierOperator	;//			运营商
        private String referer	;//			来源信息
        private String system	;//			操作系统
        private String userAgent	;//			软件环境
        private String version	;//			系统版本
        private String searchType	;//			推广来源
        private String accessUrl	;//			着陆页
        private String ip	;//			IP
        private String equipment	;//			设备
        private String region	;//			地区

        public String getCarrierOperator() {
            return carrierOperator;
        }

        public void setCarrierOperator(String carrierOperator) {
            this.carrierOperator = carrierOperator;
        }

        public String getReferer() {
            return referer;
        }

        public void setReferer(String referer) {
            this.referer = referer;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getSearchType() {
            return searchType;
        }

        public void setSearchType(String searchType) {
            this.searchType = searchType;
        }

        public String getAccessUrl() {
            return accessUrl;
        }

        public void setAccessUrl(String accessUrl) {
            this.accessUrl = accessUrl;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getEquipment() {
            return equipment;
        }

        public void setEquipment(String equipment) {
            this.equipment = equipment;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }
    public static class ServiceSessionEventHistory {

        private String eventId	;//		事件Id
        private String serviceSessionId	;//		会话Id
        private String tenantId	;//		租户Id
        private String actorId	;//		操作人Id
        private String actorName	;//		操作人昵称
        private String fromId	;//		转接自（操作人Id）
        private String fromName	;//		转接自（操作人昵称）
        private String actorTrueName	;//		操作人姓名
        private String fromState	;//		操作前状态
        private String toId	;//		转接用户Id
        private String toName	;//		转接用户姓名
        private String toState	;//		操作后状态
        private String type	;//		事件类型：Created-创建，Opened-接起，FirstReplyTime-首次响应，Transfer转接（AA-坐席转坐席，AQ-坐席转技能组，QA-技能组转坐席，QQ-技能组转技能组，RA-机器人转坐席，RQ-机器人转技能组），Closed-关闭，Aborted-待接入会话关闭
        private String serviceSessionExt	;//	会话相关事件的详细信息
        private String createDateTime	;//		事件时间
        private String timeStamp	;//		可忽略

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getServiceSessionId() {
            return serviceSessionId;
        }

        public void setServiceSessionId(String serviceSessionId) {
            this.serviceSessionId = serviceSessionId;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getActorId() {
            return actorId;
        }

        public void setActorId(String actorId) {
            this.actorId = actorId;
        }

        public String getActorName() {
            return actorName;
        }

        public void setActorName(String actorName) {
            this.actorName = actorName;
        }

        public String getFromId() {
            return fromId;
        }

        public void setFromId(String fromId) {
            this.fromId = fromId;
        }

        public String getFromName() {
            return fromName;
        }

        public void setFromName(String fromName) {
            this.fromName = fromName;
        }

        public String getActorTrueName() {
            return actorTrueName;
        }

        public void setActorTrueName(String actorTrueName) {
            this.actorTrueName = actorTrueName;
        }

        public String getFromState() {
            return fromState;
        }

        public void setFromState(String fromState) {
            this.fromState = fromState;
        }

        public String getToId() {
            return toId;
        }

        public void setToId(String toId) {
            this.toId = toId;
        }

        public String getToName() {
            return toName;
        }

        public void setToName(String toName) {
            this.toName = toName;
        }

        public String getToState() {
            return toState;
        }

        public void setToState(String toState) {
            this.toState = toState;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getServiceSessionExt() {
            return serviceSessionExt;
        }

        public void setServiceSessionExt(String serviceSessionExt) {
            this.serviceSessionExt = serviceSessionExt;
        }

        public String getCreateDateTime() {
            return createDateTime;
        }

        public void setCreateDateTime(String createDateTime) {
            this.createDateTime = createDateTime;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }
    }


    public static class ServiceSessionExt {

        private Boolean fromAgentCallback	;//		用于记录会话创建事件，会话是否是回呼
        private String  assignedAgent	;//		用于记录会话创建事件，记录会话是否指定坐席（指定：坐席的username， 未指定：null）
        private String routingPolicyType	;//		用于记录会话创建事件，会话路由信息(UserSpecifiedChannel：入口指定，ChannelData：关联指定，Channel：渠道指定， OfficialAccount：服务号指定，SpecialIdentity：特殊身份指定，Default：默认指定)
        private String  sessionCreateType	;//		会话创建的方式( “fromAgentCallback”, “fromAassignedAgent”, “fromRoutingPolicy”)
        private Boolean routingToRobot	;//		是否路由到机器人
        private String queueName	;//		路由到技能组的名称

        public Boolean getFromAgentCallback() {
            return fromAgentCallback;
        }

        public void setFromAgentCallback(Boolean fromAgentCallback) {
            this.fromAgentCallback = fromAgentCallback;
        }

        public String getAssignedAgent() {
            return assignedAgent;
        }

        public void setAssignedAgent(String assignedAgent) {
            this.assignedAgent = assignedAgent;
        }

        public String getRoutingPolicyType() {
            return routingPolicyType;
        }

        public void setRoutingPolicyType(String routingPolicyType) {
            this.routingPolicyType = routingPolicyType;
        }

        public String getSessionCreateType() {
            return sessionCreateType;
        }

        public void setSessionCreateType(String sessionCreateType) {
            this.sessionCreateType = sessionCreateType;
        }

        public Boolean getRoutingToRobot() {
            return routingToRobot;
        }

        public void setRoutingToRobot(Boolean routingToRobot) {
            this.routingToRobot = routingToRobot;
        }

        public String getQueueName() {
            return queueName;
        }

        public void setQueueName(String queueName) {
            this.queueName = queueName;
        }
    }

    public static class AgentQueue {

        private String queueId	;//
        private String queueName	;//
        private String queueType	;//
        private Integer tenantId	;//

        public String getQueueId() {
            return queueId;
        }

        public void setQueueId(String queueId) {
            this.queueId = queueId;
        }

        public String getQueueName() {
            return queueName;
        }

        public void setQueueName(String queueName) {
            this.queueName = queueName;
        }

        public String getQueueType() {
            return queueType;
        }

        public void setQueueType(String queueType) {
            this.queueType = queueType;
        }

        public Integer getTenantId() {
            return tenantId;
        }

        public void setTenantId(Integer tenantId) {
            this.tenantId = tenantId;
        }
    }

    public static class AgentUser {

        private String userId	;//		坐席ID
        private String username	;//		坐席账号（邮箱）
        private String nicename	;//		坐席昵称
        private String trueName	;//		坐席的真实姓名
        private String tenantId	;//		坐席的租户ID

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNicename() {
            return nicename;
        }

        public void setNicename(String nicename) {
            this.nicename = nicename;
        }

        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }
    }

    public static class Customer {

        private String customerId; //客户ID

        private String customerNiceName; //客户昵称

        private String customerTrueName; //客户的真实姓名

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerNiceName() {
            return customerNiceName;
        }

        public void setCustomerNiceName(String customerNiceName) {
            this.customerNiceName = customerNiceName;
        }

        public String getCustomerTrueName() {
            return customerTrueName;
        }

        public void setCustomerTrueName(String customerTrueName) {
            this.customerTrueName = customerTrueName;
        }
    }
    public static class VisitorUser {

        private String userId; //用户id

        private String nicename; //呢称

        private String username; //用户名

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNicename() {
            return nicename;
        }

        public void setNicename(String nicename) {
            this.nicename = nicename;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class Summary {

       private Integer id; //会话标签id

       private String name; //会话标签名称

       private Integer color; //会话标签颜色

       private Integer parentId; //父节点id

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getColor() {
            return color;
        }

        public void setColor(Integer color) {
            this.color = color;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }
    }
}
