package com.tinet.clink.openapi;

/**
 * @author wangll
 * @date 2019/2/19
 */
public enum PathEnum {
    //  座席设置
    CreateClient("create_client"),
    UpdateClient("update_client"),
    DeleteClient("delete_client"),
    ListClients("list_clients"),
    DescribeClient("describe_client"),
    DescribeClientObClid("describe_client_ob_clid"),
    BindClientTel("bind_client_tel"),
    BindClientTelVerification("bind_client_tel_verification"),
    BindClientTelConfirmed("bind_client_tel_confirmed"),
    UnbindClientTel("unbind_client_tel"),
    ListClientTels("list_client_tels"),
    DeleteClientTel("delete_client_tel"),
    //  队列设置
    CreateQueue("create_queue"),
    UpdateQueue("update_queue"),
    DeleteQueue("delete_queue"),
    ListQueues("list_queues"),
    DescribeQueue("describe_queue"),
    ListQueuesWithAgentAction("list_queues_with_agent_action"),

    /**
     * 置忙状态
     */
    ListEnterprisePauses("list_enterprise_pauses"),
    CreateEnterprisePauses("create_enterprise_pause"),
    DeleteEnterprisePauses("delete_enterprise_pause"),
    UpdateEnterprisePauses("update_enterprise_pause"),

    //  话机设置
    CreateExten("create_exten"),
    UpdateExten("update_exten"),
    DeleteExten("delete_exten"),
    ListExtens("list_extens"),
    DescribeExten("describe_exten"),
    // 号码设置
    ListClidNumbers("list_clid_numbers"),
    ListHotlineNumbers("list_hotline_numbers"),
    // 呼叫控制
    Online("online"),
    Offline("offline"),
    Pause("pause"),
    Unpause("unpause"),
    Callout("callout"),
    CalloutCancel("callout_cancel"),
    Unlink("unlink"),
    Refuse("refuse"),
    Transfer("transfer"),
    Interact("interact"),
    Consult("consult"),
    ConsultCancel("consult_cancel"),
    ConsultTransfer("consult_transfer"),
    ConsultThreeway("consult_threeway"),
    Unconsult("unconsult"),
    Hold("hold"),
    Unhold("unhold"),
    Mute("mute"),
    Unmute("unmute"),
    Dtmf("dtmf"),
    Investigation("investigation"),
    // 呼叫管理
    Spy("spy"),
    Threeway("threeway"),
    Whisper("whisper"),
    Disconnect("disconnect"),
    Barge("barge"),
    PauseClient("pause_client"),
    UnpauseClient("unpause_client"),
    OfflineClient("offline_client"),
    //  呼入通话记录
    ListCdrIbs("list_cdr_ibs"),
    DescribeCdrIb("describe_cdr_ib"),
    DescribeCdrIbDetails("describe_cdr_ib_details"),
    CopyCdrIbs("copy_cdr_ibs"),
    CopyCdrIbDetails("copy_cdr_ib_details"),
    // 外呼通话记录
    ListCdrObs("list_cdr_obs"),
    DescribeCdrOb("describe_cdr_ob"),
    DescribeCdrObDetails("describe_cdr_ob_details"),
    CopyCdrObs("copy_cdr_obs"),
    CopyCdrObDetails("copy_cdr_ob_details"),
    // 下载从话单录音文件
    DownloadDetailRecordFile("download_detail_record_file"),
    // sqc asr
    sqcAsr("sqc_asr"),


    //  留言记录
    ListComments("list_comments"),
    // 满意度记录
    ListInvestigations("list_investigations"),
    // 添加满意度记录
    CreateInvestigations("create_investigations"),
    // 下载通话录音文件
    DownloadRecordFile("download_record_file"),
    // 查询通话录音url
    DescribeRecordFileUrl("describe_record_file_url"),
    // 语音导航
    ListIvrs("list_ivrs"),
    ListIvrNodes("list_ivr_nodes"),
    // 座席状态监控
    AgentStatus("agent_status"),
    // 座席状态详情
    AgentStatusDetail("agent_status_detail"),
    // 队列状态监控
    QueueStatus("queue_status"),
    //客户资料-获取可用查询参数
    CustomerParam("customer_params"),
    //客户资料-查询客户资料列表
    ListCustomers("list_customers"),
    //创建客户资料
    CreateCustomer("create_customer"),

    //更新客户资料
    UpdateCustomer("update_customer"),

    //更新客户资料
    UpdateCustomerByExternalId("update_customer_by_external_id"),
    //创建客户资料
    ListCustomerField("list_customer_field"),
    // 删除客户资料
    DeleteCustomer("delete_customer"),

    //短信发送
    SmsSend("sms_send"),
    //手机号码状态检测
    PhoneStatus("phone_status"),

    //短信发送
    SsoLoginToken("login_token"),

    //----------------------------------------------↓呼叫中心-报表↓----------------------------------------------//

    //座席工作量报表
    StatClientWorkload("stat_client_workload"),
    //队列报表
    StatQueue("stat_queue"),
    //座席状态统计报表
    StatClientStatus("stat_client_status"),
    //外呼报表-预览外呼
    StatPreviewOb("stat_preview_ob"),
    //中继报表-来电分析
    StatHotlineIb("stat_hotline_ib"),
    //中继报表-外呼接听率分析
    StatHotlineObAnsweredRate("stat_hotline_ob_answered_rate"),
    //IVR报表-IVR统计
    StatIvrList("stat_ivr_list"),
    //IVR报表-IVR节点统计
    StatIvrNodes("stat_ivr_nodes"),
    //满意度报表-按座席统计
    StatInvestigationByCno("stat_investigation_by_cno"),
    //满意度报表-按热线号码
    StatInvestigationByHotlines("stat_investigation_by_hotlines"),
    //呼入报表-按地区统计
    StatCallIbArea("stat_call_ib_area"),
    //----------------------------------------------↑呼叫中心-报表↑----------------------------------------------//


    // webcall
    Webcall("webcall"),

    //查询业务记录的自定义字段
    ListBusinessField("list_business_customize_field"),

    //查询业务记录
    ListBusiness("list_business"),

    //查询业务记录
    GetBusinessDetail("get_business_detail"),

    // 在线客服 - 获取会话记录
    ChatRecord("copy_chat_records"),
    // 在线客服 - 查询会话详情
    ChatRecordInfo("get_chat_record_info"),
    // 在线客服 - 获取会话详情
    ChatDetail("copy_chat_details"),
    // 在线客服 - 获取聊天记录
    ChatMessage("copy_chat_messages"),
    // 在线客服 - 获取聊天记录明细
    ChatMessageDetail("list_chat_messages"),
    // 在线客服 - 获取会话满意度
    ChatInvestigation("copy_chat_investigations"),
    // 在线客服 - 获取会话留言
    ChatComment("copy_chat_comments"),
    //在线客服 - 座席工作量报表
    StatChatClientWorkload("stat_chat_client_workload"),
    //队列报表
    StatChatQueueWorkload("stat_chat_queue_workload"),

    //-------------------在线客服API--------
    ChatVisitorOpenSession("chat_visitor_open_session"),
    ChatVisitorCloseSession("chat_visitor_close_session"),
    ChatSubmitInvestigation("chat_submit_investigation"),
    ChatQuitQueue("chat_quit_queue"),
    ChatRobotTransfer("chat_robot_transfer"),
    ChatMessageToClient("chat_message_to_client"),
    ChatClientOpenSession("chat_client_open_session"),
    ChatClientCloseSession("chat_client_close_session"),
    ChatClientInvestigation("chat_client_investigation"),
    ChatMessageToVisitor("chat_message_to_visitor"),
    ChatClientWithdraw("chat_client_withdraw"),
    ChatClientLogin("chat_client_login"),
    ChatClientLogout("chat_client_logout"),
    ChatQueueAgentStatus("chat_queue_status"),
    //-------------------在线客服API--------


    //-------------------知识库API--------
    CreateArticle("create_article"),
    UpdateArticle("update_article"),
    DeleteArticle("delete_article"),
    DescribeArticle("describe_article"),
    ListArticles("list_articles"),
    ListRepositories("list_repositories"),
    ListDirectories("list_directories"),
    //-------------------知识库API--------

    //-------------------机器人知识库API--------
    CreateStandardQuestion("create_standard_question"),
    UpdateStandardQuestion("update_standard_question"),
    DeleteStandardQuestion("delete_standard_question"),
    CreateAnswer("create_answer"),
    UpdateAnswer("update_answer"),
    DeleteAnswer("delete_answer"),
    CreateCorpus("create_corpus"),
    UpdateCorpus("update_corpus"),
    DeleteCorpus("delete_corpus"),
    //-------------------机器人知识库API--------

//    -----工单记录-------

    //查询工单记录
    ListTicket("list_ticket"),

    // 获取工单详情
    GetTicketDetail("get_ticket_detail"),

    ListTicketCategory("list_ticket_category"),

    ListTicketWorkflow("list_ticket_workflow"),

    SaveTicket("save_ticket"),
    UpdateTicket("update_ticket"),
    // 获取表单详情
    GetFormDetail("get_form_detail"),
    // 流转工单
    FlowTicket("flow_ticket"),
    // 保存工单
    StoreTicket("store_ticket"),
    // 评论工单
    CommentTicket("comment_ticket"),
    // 获取工单文件 url
    GetTicketFileUrl("get_ticket_file_url"),

    /**
     * 黑白名单相关请求
     */
    CreateTelRestrict("create_restrict_tel"),
    DeleteTelRestrict("delete_restrict_tel"),
    ListTelRestrict("list_restrict_tel"),
    ListTelRestrictSetting("describe_restrict_tel_setting"),
    UpdateTelRestrictSetting("update_restrict_tel_setting"),

    /**
     * 日志
     */
    ListLogOperation("list_log_operation"),
    ListLogAgent("list_log_agent"),
    ListLogAgentDuration("list_log_agent_duration"),
    ListLogLogin("list_log_login"),
    /**
     * rasr转写记录
     */
    BotAsr("bot_asr"),
    /**
     * 满意度配置
     */
    investigationSetting("investigation_setting"),

    /**
     * 工具条authToken获取
     */
    wsAuthToken("ws_auth_token"),

    /**
     * 工具条agentToken获取
     */
    wsAgentToken("ws_agent_token"),

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
