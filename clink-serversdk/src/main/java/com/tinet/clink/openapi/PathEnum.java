package com.tinet.clink.openapi;

/**
 * @author wangll
 * @date 2019/2/19
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public enum PathEnum {
    //  座席设置
    CreateClient("cc/create_client"),
    UpdateClient("cc/update_client"),
    DeleteClient("cc/delete_client"),
    ListClients("cc/list_clients"),
    DescribeClient("cc/describe_client"),
    DescribeClientObClid("cc/describe_client_ob_clid"),
    BindClientTel("cc/bind_client_tel"),
    BindClientTelVerification("cc/bind_client_tel_verification"),
    BindClientTelConfirmed("cc/bind_client_tel_confirmed"),
    UnbindClientTel("cc/unbind_client_tel"),
    ListClientTels("cc/list_client_tels"),
    DeleteClientTel("cc/delete_client_tel"),
    //  队列设置
    CreateQueue("cc/create_queue"),
    UpdateQueue("cc/update_queue"),
    DeleteQueue("cc/delete_queue"),
    ListQueues("cc/list_queues"),
    DescribeQueue("cc/describe_queue"),
    ListQueuesWithAgentAction("cc/list_queues_with_agent_action"),

    /**
     * 置忙状态
     */
    ListEnterprisePauses("cc/list_enterprise_pauses"),
    CreateEnterprisePauses("cc/create_enterprise_pause"),
    DeleteEnterprisePauses("cc/delete_enterprise_pause"),
    UpdateEnterprisePauses("cc/update_enterprise_pause"),

    //  话机设置
    CreateExten("cc/create_exten"),
    UpdateExten("cc/update_exten"),
    DeleteExten("cc/delete_exten"),
    ListExtens("cc/list_extens"),
    DescribeExten("cc/describe_exten"),
    // 号码设置
    ListClidNumbers("cc/list_clid_numbers"),
    ListHotlineNumbers("cc/list_hotline_numbers"),
    // 呼叫控制
    Online("cc/online"),
    Offline("cc/offline"),
    Pause("cc/pause"),
    Unpause("cc/unpause"),
    Callout("cc/callout"),
    CalloutCancel("cc/callout_cancel"),
    Unlink("cc/unlink"),
    Refuse("cc/refuse"),
    Transfer("cc/transfer"),
    Interact("cc/interact"),
    Consult("cc/consult"),
    ConsultCancel("cc/consult_cancel"),
    ConsultTransfer("cc/consult_transfer"),
    ConsultThreeway("consult_cc/threeway"),
    Unconsult("cc/unconsult"),
    Hold("cc/hold"),
    Unhold("cc/unhold"),
    Mute("cc/mute"),
    Unmute("cc/unmute"),
    Dtmf("cc/dtmf"),
    Investigation("cc/investigation"),
    // 呼叫管理
    Spy("cc/spy"),
    Threeway("threeway"),
    Whisper("cc/whisper"),
    Disconnect("cc/disconnect"),
    Barge("cc/barge"),
    PauseClient("cc/pause_client"),
    UnpauseClient("cc/unpause_client"),
    OfflineClient("cc/offline_client"),
    //  呼入通话记录
    ListCdrIbs("cc/list_cdr_ibs"),
    DescribeCdrIb("cc/describe_cdr_ib"),
    DescribeCdrIbDetails("cc/describe_cdr_ib_details"),
    CopyCdrIbs("cc/copy_cdr_ibs"),
    CopyCdrIbDetails("cc/copy_cdr_ib_details"),
    // 外呼通话记录
    ListCdrObs("cc/list_cdr_obs"),
    DescribeCdrOb("cc/describe_cdr_ob"),
    DescribeCdrObDetails("cc/describe_cdr_ob_details"),
    CopyCdrObs("cc/copy_cdr_obs"),
    CopyCdrObDetails("cc/copy_cdr_ob_details"),
    // 下载从话单录音文件
    DownloadDetailRecordFile("cc/download_detail_record_file"),
    // sqc asr
    sqcAsr("cc/sqc_asr"),


    //  留言记录
    ListComments("cc/list_comments"),
    // 满意度记录
    ListInvestigations("cc/list_investigations"),
    // 添加满意度记录
    CreateInvestigations("cc/create_investigations"),
    // 下载通话录音文件
    DownloadRecordFile("cc/download_record_file"),
    // 查询通话录音url
    DescribeRecordFileUrl("cc/describe_record_file_url"),
    // 语音导航
    ListIvrs("cc/list_ivrs"),
    ListIvrNodes("cc/list_ivr_nodes"),
    // 座席状态监控
    AgentStatus("cc/agent_status"),
    // 座席状态详情
    AgentStatusDetail("cc/agent_status_detail"),
    // 队列状态监控
    QueueStatus("cc/queue_status"),
    //客户资料-获取可用查询参数
    CustomerParam("crm/customer_params"),
    //客户资料-查询客户资料列表
    ListCustomers("crm/list_customers"),
    //创建客户资料
    CreateCustomer("crm/create_customer"),

    //更新客户资料
    UpdateCustomer("crm/update_customer"),

    //更新客户资料
    UpdateCustomerByExternalId("crm/update_customer_by_external_id"),
    //创建客户资料
    ListCustomerField("crm/list_customer_field"),
    // 删除客户资料
    DeleteCustomer("crm/delete_customer"),

    //短信发送
    SmsSend("cc/sms_send"),
    //手机号码状态检测
    PhoneStatus("cc/phone_status"),

    //短信发送
    SsoLoginToken("cc/login_token"),

    //----------------------------------------------↓呼叫中心-报表↓----------------------------------------------//

    //座席工作量报表
    StatClientWorkload("cc/stat_client_workload"),
    //队列报表
    StatQueue("cc/stat_queue"),
    //座席状态统计报表
    StatClientStatus("cc/stat_client_status"),
    //外呼报表-预览外呼
    StatPreviewOb("cc/stat_preview_ob"),
    //中继报表-来电分析
    StatHotlineIb("cc/stat_hotline_ib"),
    //中继报表-外呼接听率分析
    StatHotlineObAnsweredRate("cc/stat_hotline_ob_answered_rate"),
    //IVR报表-IVR统计
    StatIvrList("cc/stat_ivr_list"),
    //IVR报表-IVR节点统计
    StatIvrNodes("cc/stat_ivr_nodes"),
    //满意度报表-按座席统计
    StatInvestigationByCno("cc/stat_investigation_by_cno"),
    //满意度报表-按热线号码
    StatInvestigationByHotlines("cc/stat_investigation_by_hotlines"),
    //呼入报表-按地区统计
    StatCallIbArea("cc/stat_call_ib_area"),
    //----------------------------------------------↑呼叫中心-报表↑----------------------------------------------//


    // webcall
    Webcall("cc/webcall"),

    //查询业务记录的自定义字段
    ListBusinessField("crm/list_business_customize_field"),

    //查询业务记录
    ListBusiness("crm/list_business"),

    //查询业务记录
    GetBusinessDetail("crm/get_business_detail"),

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
    //在线客服 - 座席工作量报表（新）
    StatChatClientWorkloadNew("livechat/stat_chat_client_workload_new"),
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
    ChatQueueAgentStatus("livechat/chat_queue_status"),
    // 在线客服 - 获取访客未读消息数
    ChatVisitorUnreadCount("livechat/chat_visitor_unread_count"),
    //-------------------在线客服API--------


    //-------------------知识库API--------
    CreateArticle("kb/create_article"),
    UpdateArticle("kb/update_article"),
    DeleteArticle("kb/delete_article"),
    DescribeArticle("kb/describe_article"),
    ListArticles("kb/list_articles"),
    ListRepositories("kb/list_repositories"),
    ListDirectories("kb/list_directories"),
    //-------------------知识库API--------

    //-------------------机器人知识库API--------
    ListStandardQuestion("kb/list_standard_question"),
    CreateStandardQuestion("kb/create_standard_question"),
    UpdateStandardQuestion("kb/update_standard_question"),
    DeleteStandardQuestion("kb/delete_standard_question"),
    ListAnswer("kb/list_answer"),
    CreateAnswer("kb/create_answer"),
    UpdateAnswer("kb/update_answer"),
    DeleteAnswer("kb/delete_answer"),
    ListCorpus("kb/list_corpus"),
    CreateCorpus("kb/create_corpus"),
    UpdateCorpus("kb/update_corpus"),
    DeleteCorpus("kb/delete_corpus"),
    CreateCategory("kb/create_category"),
    UpdateCategory("kb/update_category"),
    DeleteCategory("kb/delete_category"),
    MediaUrl("kb/media_url"),
    //-------------------机器人知识库API--------

//    -----工单记录-------

    //查询工单记录
    ListTicket("ticket/list_ticket"),

    // 获取工单详情
    GetTicketDetail("ticket/get_ticket_detail"),

    ListTicketCategory("ticket/list_ticket_category"),

    ListTicketWorkflow("ticket/list_ticket_workflow"),

    SaveTicket("ticket/save_ticket"),
    UpdateTicket("ticket/update_ticket"),
    // 获取表单详情
    GetFormDetail("ticket/get_form_detail"),
    // 获取表单详情
    GetSysFormDetail("ticket/get_sys_form_detail"),
    // 流转工单
    FlowTicket("ticket/flow_ticket"),
    // 保存工单
    StoreTicket("ticket/store_ticket"),
    // 评论工单
    CommentTicket("ticket/comment_ticket"),
    // 关闭工单
    CloseTicket("ticket/close_ticket"),
    // 完成工单
    FinishTicket("ticket/finish_ticket"),
    // 获取工单文件 url
    GetTicketFileUrl("ticket/get_ticket_file_url"),
    // 根据外部参数或ID查询工单插件
    GetTicketPlugin("ticket/get_ticket_plugin"),
    // 获取工单字段列表
    ListTicketFields("ticket/list_ticket_fields"),
    // 更新工单字段选项值
    updateTicketFieldProperty("ticket/update_ticket_field_property"),

    /**
     * 黑白名单相关请求
     */
    CreateTelRestrict("cc/create_restrict_tel"),
    DeleteTelRestrict("cc/delete_restrict_tel"),
    ListTelRestrict("cc/list_restrict_tel"),
    ListTelRestrictSetting("cc/describe_restrict_tel_setting"),
    UpdateTelRestrictSetting("cc/update_restrict_tel_setting"),

    /**
     * 日志
     */
    ListLogOperation("cc/list_log_operation"),
    ListLogAgent("cc/list_log_agent"),
    ListLogAgentDuration("cc/list_log_agent_duration"),
    ListLogLogin("cc/list_log_login"),
    /**
     * rasr转写记录
     */
    BotAsr("cc/bot_asr"),
    /**
     * 满意度配置
     */
    investigationSetting("cc/investigation_setting"),

    /**
     * 工具条authToken获取
     */
    wsAuthToken("cc/ws_auth_token"),

    /**
     * 工具条agentToken获取
     */
    wsAgentToken("cc/ws_agent_token"),

    /**
     * 外呼任务创建
     */
    CreateTaskProperty("create_task_property");
    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
