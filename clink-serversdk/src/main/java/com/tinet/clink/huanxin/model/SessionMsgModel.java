package com.tinet.clink.huanxin.model;


import java.util.Date;
import java.util.List;

public class SessionMsgModel {

    private String msgId	;//	消息Id
    private Integer tenantId	;//	租户Id
    private String sessionServiceId	;//	会话Id
    private String messageType	;//	消息类型

    private FromUser fromUser;
    private String contentType	;//		消息内容类型

    private Body body;

    private Long chatGroupSeqId;
    private Long sessionServiceSeqId;//
    private Long createDateTime;//
    private Long timestamp;//
    private Long createMicroTimestamp;//

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getSessionServiceId() {
        return sessionServiceId;
    }

    public void setSessionServiceId(String sessionServiceId) {
        this.sessionServiceId = sessionServiceId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public FromUser getFromUser() {
        return fromUser;
    }

    public void setFromUser(FromUser fromUser) {
        this.fromUser = fromUser;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Long getChatGroupSeqId() {
        return chatGroupSeqId;
    }

    public void setChatGroupSeqId(Long chatGroupSeqId) {
        this.chatGroupSeqId = chatGroupSeqId;
    }

    public Long getSessionServiceSeqId() {
        return sessionServiceSeqId;
    }

    public void setSessionServiceSeqId(Long sessionServiceSeqId) {
        this.sessionServiceSeqId = sessionServiceSeqId;
    }

    public Long getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Long createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getCreateMicroTimestamp() {
        return createMicroTimestamp;
    }

    public void setCreateMicroTimestamp(Long createMicroTimestamp) {
        this.createMicroTimestamp = createMicroTimestamp;
    }

    public static class FromUser {
        private Integer tenantId	;//	租户Id
        private String userId	;//	Id
        private String userType	;//	类型
        private String userScope	;//	用户层级
        private String nicename	;//	昵称
        private String scope	;//	用户层级
        private String bizId	;//	租户Id
        private String status	;//	用户状态
        private String role;

        public Integer getTenantId() {
            return tenantId;
        }

        public void setTenantId(Integer tenantId) {
            this.tenantId = tenantId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserScope() {
            return userScope;
        }

        public void setUserScope(String userScope) {
            this.userScope = userScope;
        }

        public String getNicename() {
            return nicename;
        }

        public void setNicename(String nicename) {
            this.nicename = nicename;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getBizId() {
            return bizId;
        }

        public void setBizId(String bizId) {
            this.bizId = bizId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public static class Bodies {

        private String msg;

        private String type;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Ext {

        private Weichat weichat;

        public Weichat getWeichat() {
            return weichat;
        }

        public void setWeichat(Weichat weichat) {
            this.weichat = weichat;
        }
    }


    public static class Event {

        private String eventName;

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }
    }

    public static class ServiceSession {

        private String serviceSessionId	;//	会话Id
        private Integer robotId	;//	机器人Id
        private String state	;//	会话状态
        private Long messageSeqId	;//	最大消息序列自增标记
        private String agentUserId	;//	坐席Id
        private Integer agentUserType	;//	坐席类型
        private Boolean expire	;//	会话是否过期

        public String getServiceSessionId() {
            return serviceSessionId;
        }

        public void setServiceSessionId(String serviceSessionId) {
            this.serviceSessionId = serviceSessionId;
        }

        public Integer getRobotId() {
            return robotId;
        }

        public void setRobotId(Integer robotId) {
            this.robotId = robotId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Long getMessageSeqId() {
            return messageSeqId;
        }

        public void setMessageSeqId(Long messageSeqId) {
            this.messageSeqId = messageSeqId;
        }

        public String getAgentUserId() {
            return agentUserId;
        }

        public void setAgentUserId(String agentUserId) {
            this.agentUserId = agentUserId;
        }

        public Integer getAgentUserType() {
            return agentUserType;
        }

        public void setAgentUserType(Integer agentUserType) {
            this.agentUserType = agentUserType;
        }

        public Boolean getExpire() {
            return expire;
        }

        public void setExpire(Boolean expire) {
            this.expire = expire;
        }
    }

    public static class OfficialAccount {

        private String official_account_id;
        private String name;
        private String type;
        private String image;

        public String getOfficial_account_id() {
            return official_account_id;
        }

        public void setOfficial_account_id(String official_account_id) {
            this.official_account_id = official_account_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
    public static class Agent {
        private String avatar;
        private String agentNumber;
        private String trueName;
        private String userId;
        private String userType;
        private String userNickname;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAgentNumber() {
            return agentNumber;
        }

        public void setAgentNumber(String agentNumber) {
            this.agentNumber = agentNumber;
        }

        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public void setUserNickname(String userNickname) {
            this.userNickname = userNickname;
        }
    }
    public static class Weichat {
        private String msgId	;//	消息Id
        private String originType	;//	渠道类型
        private Agent agent;
        private String queueId	;//	技能组Id
        private String queueName	;//	技能组名称
        private Event event;
        protected ServiceSession service_session;
        private Boolean hide_flag ; //	消息是否上屏
        private OfficialAccount official_account;
        private String schedule_info;

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getOriginType() {
            return originType;
        }

        public void setOriginType(String originType) {
            this.originType = originType;
        }

        public Agent getAgent() {
            return agent;
        }

        public void setAgent(Agent agent) {
            this.agent = agent;
        }

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

        public Event getEvent() {
            return event;
        }

        public void setEvent(Event event) {
            this.event = event;
        }

        public ServiceSession getService_session() {
            return service_session;
        }

        public void setService_session(ServiceSession service_session) {
            this.service_session = service_session;
        }

        public Boolean getHide_flag() {
            return hide_flag;
        }

        public void setHide_flag(Boolean hide_flag) {
            this.hide_flag = hide_flag;
        }

        public OfficialAccount getOfficial_account() {
            return official_account;
        }

        public void setOfficial_account(OfficialAccount official_account) {
            this.official_account = official_account;
        }

        public String getSchedule_info() {
            return schedule_info;
        }

        public void setSchedule_info(String schedule_info) {
            this.schedule_info = schedule_info;
        }
    }

    public static class Body {

        private List<Bodies> bodies;

        private Ext ext;

        private String from	;//	发送者昵称
        private String to	;//	接受者昵称
        private String channelType	;//	关联类型
        private Date timestamp	;//	时间戳
        private Integer tenantId	;//	租户Id
        private String visitorUserId	;//	访客Id
        private String originType	;//	渠道类型
        private Integer channel_id	;//	关联Id

        public List<Bodies> getBodies() {
            return bodies;
        }

        public void setBodies(List<Bodies> bodies) {
            this.bodies = bodies;
        }

        public Ext getExt() {
            return ext;
        }

        public void setExt(Ext ext) {
            this.ext = ext;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getChannelType() {
            return channelType;
        }

        public void setChannelType(String channelType) {
            this.channelType = channelType;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getTenantId() {
            return tenantId;
        }

        public void setTenantId(Integer tenantId) {
            this.tenantId = tenantId;
        }

        public String getVisitorUserId() {
            return visitorUserId;
        }

        public void setVisitorUserId(String visitorUserId) {
            this.visitorUserId = visitorUserId;
        }

        public String getOriginType() {
            return originType;
        }

        public void setOriginType(String originType) {
            this.originType = originType;
        }

        public Integer getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(Integer channel_id) {
            this.channel_id = channel_id;
        }
    }
}
