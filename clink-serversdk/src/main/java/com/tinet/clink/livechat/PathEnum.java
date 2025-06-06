package com.tinet.clink.livechat;

/**
 * @author wangll
 * @date 2019/2/19
 */
public enum PathEnum {
    // 在线客服 - 获取会话记录
    ChatRecord("livechat/copy_chat_records"),
    // 在线客服 - 查询会话详情
    ChatRecordInfo("livechat/get_chat_record_info"),
    // 在线客服 - 获取会话详情
    ChatDetail("livechat/copy_chat_details"),
    // 在线客服 - 获取聊天记录
    ChatMessage("livechat/copy_chat_messages"),
    // 在线客服 - 获取聊天记录明细
    ChatMessageDetail("livechat/list_chat_messages"),
    // 在线客服 - 获取会话满意度
    ChatInvestigation("livechat/copy_chat_investigations"),
    // 在线客服 - 获取会话留言
    ChatComment("livechat/copy_chat_comments"),
    //在线客服 - 座席工作量报表
    StatChatClientWorkload("livechat/stat_chat_client_workload"),
    //在线客服 - 座席工作台首页数据
    StatChatClientWorkloadNew("livechat/stat_chat_client_workload_new"),
    //在线客服 - 座席工作质量报表
    StatChatClientWorkQuality("livechat/stat_chat_client_work_quality_V2"),
    //在线客服 - 座席工作量报表(新)
    StatChatClientEffort("livechat/stat_chat_client_workload_V2"),
    //队列报表
    StatChatQueueWorkload("livechat/stat_chat_queue_workload"),

    //-------------------在线客服API--------
    ChatVisitorOpenSession("livechat/chat_visitor_open_session"),
    ChatVisitorCloseSession("livechat/chat_visitor_close_session"),
    ChatSubmitInvestigation("livechat/chat_submit_investigation"),
    ChatQuitQueue("livechat/chat_quit_queue"),
    ChatRobotTransfer("livechat/chat_robot_transfer"),
    ChatMessageToClient("livechat/chat_message_to_client"),
    ChatClientOpenSession("livechat/chat_client_open_session"),
    ChatClientCloseSession("livechat/chat_client_close_session"),
    ChatClientInvestigation("livechat/chat_client_investigation"),
    ChatMessageToVisitor("livechat/chat_message_to_visitor"),
    ChatClientWithdraw("livechat/chat_client_withdraw"),
    ChatClientLogin("livechat/chat_client_login"),
    ChatClientLogout("livechat/chat_client_logout"),

    ChatClientStatusChange("livechat/chat_client_status_change"),
    ChatQueueAgentStatus("livechat/chat_queue_status"),
    // 在线客服 - 获取访客未读消息数
    ChatVisitorUnreadCount("livechat/chat_visitor_unread_count"),

    // 在线客服-配置修改-网页渠道
    ChatWebSettingUpdate("livechat/update_chat_web_setting"),

    // 在线客服-配置复制-网页渠道
    ChatWebSettingCopy("livechat/copy_chat_web_setting"),

    //在线客服 - 座席工作考勤报表
    StatChatClientAttendance("livechat/stat_chat_client_attendance"),

    //在线客服 - 满意度报表-按座席统计
    statInvestigationCnoPage("livechat/stat_investigation_cno_page"),

    //在线客服 - 队列报表_按时间统计
    statQueuePeriodList("livechat/stat_queue_period_list"),

    //在线客服 - 队列报表_按队列统计
    statQueueQnoPage("livechat/stat_queue_qno_page"),

    //在线客服 - 查询聊天记录（新）
    ListSessionMessages("livechat/record_session_message_list"),

    //在线客服 - 查询会话详情（新）
    RecordSession("livechat/record_session"),

    //在线客服 - 同步会话记录（新）
    RecordSessionPage("livechat/record_session_page"),

    //在线客服 - 同步座席&机器人会话记录
    RecordSessionDetailPage("livechat/record_session_detail_page"),

    //在线客服 - 同步满意度记录（新）
    RecordInvestigationsPage("livechat/record_investigations_page"),

    //在线客服 - 满意度报表-按队列统计
    StatInvestigationQnoPage("livechat/stat_investigation_qno_page"),

    //在线客服 - 会话量报表-按会话统计
    StatSessionPeriodList("livechat/stat_session_period_list"),

    //在线客服 - 会话量报表_按接入号统计
    StatSessionAppPage("livechat/stat_session_app_page"),

    //在线客服 - 座席工作量报表（新）
    StatClientCnoPage("livechat/stat_client_cno_page"),

    //在线客服 - 座席考勤报表（新）
    StatClientAttendanceCnoPage("livechat/stat_client_attendance_cno_page"),


    ;
    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
