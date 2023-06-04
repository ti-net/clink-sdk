package com.tinet.clink.cc;

/**
 * @author wangll
 * @date 2019/2/19
 */
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
    //  通话记录
    ListTodayCdrsByCno("cc/list_today_cdrs_by_cno"),
    ListHistoryCdrs("cc/list_history_cdrs"),

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
    /**
     * 座席当日状态小记
     */
    AgentStatusSubtotal("/cc/agent_status_subtotal"),
    // 队列状态监控
    QueueStatus("cc/queue_status"),
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
    //外呼报表-主叫外呼
    StatDirectOb("cc/stat_direct_ob"),
    //----------------------------------------------↑呼叫中心-报表↑----------------------------------------------//

    // webcall
    Webcall("cc/webcall"),


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
    CreateTaskProperty("cc/create_task_property"),
    /**
     * 外呼任务执行状态
     */
    TaskPropertyExecStatuses("cc/task_property_exec_statuses"),
    /**
     * 外呼任务表单模版
     */
    TaskPropertyForm("cc/task_property_form"),
    /**
     * 座席外呼任务列表
     */
    ListAgentTaskProperties("cc/list_agent_task_properties"),
    /**
     * 座席外呼任务明细列表
     */
    ListAgentTaskInventories("cc/list_agent_task_inventories"),
    /**
     * 座席外呼任务明细详情
     */
    AgentTaskInventoryDetail("cc/agent_task_inventory_detail"),
    /**
     * 更新外呼任务明细处理状态
     */
    UpdateTaskInventoryHandleStatus("cc/update_task_inventory_handle_status"),
    /**
     * 更新外呼任务明细表单信息
     */
    UpdateTaskInventoryFormInfo("cc/update_task_inventory_form_info"),
    ;
    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
