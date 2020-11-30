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
    //  留言记录
    ListComments("list_comments"),
    // 满意度记录
    ListInvestigations("list_investigations"),
    // 下载通话录音文件
    DownloadRecordFile("download_record_file"),
    // 查询通话录音url
    DescribeRecordFileUrl("describe_record_file_url"),
    // 语音导航
    ListIvrs("list_ivrs"),
    ListIvrNodes("list_ivr_nodes"),
    // 座席状态监控
    AgentStatus("agent_status"),
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

    //----------------------------------------------↑呼叫中心-报表↑----------------------------------------------//


    // webcall
    Webcall("webcall"),

    //查询业务记录的自定义字段
    ListBusinessField("list_business_customize_field"),

    //查询业务记录
    ListBusiness("list_business"),

    // 在线客服 - 获取会话记录
    ChatRecord("copy_chat_records"),
    // 在线客服 - 获取会话详情
    ChatDetail("copy_chat_details"),
    // 在线客服 - 获取聊天记录
    ChatMessage("copy_chat_messages"),
    // 在线客服 - 获取会话满意度
    ChatInvestigation("copy_chat_investigations"),
    // 在线客服 - 获取会话留言
    ChatComment("copy_chat_comments"),
    //在线客服 - 座席工作量报表
    StatChatClientWorkload("stat_chat_client_workload"),
    //队列报表
    StatChatQueueWorkload("stat_chat_queue_workload"),

//    -----工单记录-------

    //查询工单记录
    ListTicket("list_ticket"),

    // 获取工单详情
    GetTicketDetail("get_ticket_detail"),

    ListTicketCategory("list_ticket_category"),

    ListTicketWorkflow("list_ticket_workflow"),

    SaveTicket("save_ticket"),
    UpdateTicket("update_ticket");

    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
