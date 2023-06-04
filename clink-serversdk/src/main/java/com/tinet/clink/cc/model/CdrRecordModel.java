package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for:
 * 通话记录
 *
 * @author : Tinet-yinzk
 * @date : 2023/6/5 00:08
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CdrRecordModel {
    /**
     * 通话记录主通道唯一标识
     */
    private String mainUniqueId;
    /**
     * 通话记录详情唯一标识
     */
    private String uniqueId;
    /**
     * 通话记录 Id
     */
    private String callId;
    /**
     * 呼叫类型
     */
    private String callType;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 接通时间
     */
    private Long bridgeTime;
    /**
     * 通话时长
     */
    private Integer bridgeDuration;
    /**
     * 总时长
     */
    private Integer totalDuration;
    /**
     * 客户来电号码，带区号
     */
    private String customerNumber;
    /**
     * 客户来电号码加密串
     */
    private String customerNumberEncrypt;
    /**
     * 客户号码区号
     */
    private String customerAreaCode;
    /**
     * 客户来电省份
     */
    private String customerProvince;
    /**
     * 客户来电城市
     */
    private String customerCity;
    /**
     * 热线号码
     */
    private String hotline;
    /**
     * 呼叫结果
     */
    private String sipCause;
    /**
     * 挂断方
     */
    private String endReason;
    /**
     * 座席电话
     */
    private String clientNumber;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 座席名称
     */
    private String clientName;
    /**
     * 接听状态。取值范围如下：0: 全部 1: 座席接听 2: 已呼叫座席，座席未接听  3: 系统接听 4: 系统未接听-IVR配置错误 5: 系统未接听-停机
     * 6: 系统未接听-欠费 7: 系统未接听-黑名单 8: 系统未接听-未注册 9: 系统未接听-彩铃 10: 系统未接听-网上400
     * 11: 系统未接听-呼叫超出营帐中设置的最大限制   12: 系统未接听-客户呼入系统后在系统未应答前挂机 13: 其他错误 默认值为0
     */
    private String status;
    /**
     * IVR名称
     */
    private String ivrName;
    /**
     * 队列号
     */
    private String qno;
    /**
     * 标记。取值范围如下： 为空表示全部， 1:留言 2:转移 3:咨询 4:三方 5:传真接收 6:会议 7:交互 8:IVR中放弃 9:已进入IVR 10:未进入IVR 11:队列中放弃 12:队列中溢出 注： 其中mark值2,3,4,7仅在status=1(座席接听)时有效， mark值1,5,6,8,9,10,11,12在status=3(系统接听)时有效
     */
    private Integer mark;
    /**
     * 备注
     */
    private String markData;
    /**
     * 满意度按键值；当键值为 null 时，说明用户无按键操作
     */
    private Integer keys;
    /**
     * 虚拟号码
     */
    private String xnumber;
    /**
     * 标签
     */
    private String[] tags;
    /**
     * 客户为VIP,0:否，1：是
     */
    private Integer customerVip;
    /**
     * 客户响铃时间
     */
    private Long customerRingingTime;
    /**
     * 座席响铃时间
     */
    private Long clientRingingTime;
    /**
     * 排队时长
     */
    private Long ibWaitDuration;
    /**
     * 队列名称
     */
    private String qname;
    /**
     * 外呼任务id
     */
    private Integer taskId;
    /**
     * 外呼任务名称
     */
    private String taskName;
    /**
     * 外呼任务详情id
     */
    private Integer taskInventoryId;
    /**
     * 进入队列时间
     */
    private Long joinQueueTime;
    /**
     * 离开队列时间
     */
    private Long leaveQueueTime;
    /**
     * 满意度值
     */
    private Integer investigationKeys;
    /**
     * 座席振铃时长
     */
    private Integer ibRingingDuration;
    /**
     * 语音播报时长
     */
    private Integer sayVoiceDuration;
    /**
     * 座席振铃时间
     */
    private Long ringTime;
    /**
     * 队列及时应答:0: 否 1: 是 2: -
     */
    private Integer queueAnswerInTime;
    /**
     * 是否邀评 1: 是  0: 否
     */
    private Integer evaluation;
    /**
     * 中继号码
     */
    private String numberTrunk;
    /**
     * 客户所在邮编
     */
    private String customerPostCode;
    /**
     * 首次进入队列时间
     */
    private Long firstJoinQueueTime;
    /**
     * 录音文件名
     */
    private String recordFile;
    /**
     * 接听状态映射
     */
    private String statusCode;

}
