package com.tinet.clink.core.utils;


import com.tinet.clink.openapi.constant.CallType;
import com.tinet.clink.openapi.constant.CdrStatus;
import com.tinet.clink.openapi.enums.SipCauseEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * @author libin
 * @date 2022/02/24
 **/
public class CommonUtils {

    /**
     * 呼入-人工接听
     */
    private static List<Integer> IB_ANSWER = Arrays.asList(1);
    /**
     * 呼入-人工未接听
     */
    private static List<Integer> IB_NO_ANSWER = Arrays.asList(2);
    /**
     * 呼入-系统应答
     */
    private static List<Integer> IB_SYSTEM_ANSWER = Arrays.asList(3);
    /**
     * 呼入-系统未应答
     */
    private static List<Integer> IB_SYSTEM_NO_ANSWER = Arrays.asList(4, 5, 6, 7, 8, 9, 10, 11, 12, 13);

    /**
     * 外呼-客户未接听
     */
    private static List<Integer> OB_CUSTOMER_NO_ANSWER = Arrays.asList(31, 32, 40, 50, 51, 20, 21, 25, 26, 27, 61, 62);
    /**
     * 外呼-座席未接听
     */
    private static List<Integer> OB_AGENT_NO_ANSWER = Arrays.asList(30, 42, 41, 22, 23, 60);

    /**
     * 外呼-双方接听
     */
    private static List<Integer> OB_BRIDGED = Arrays.asList(33, 43, 52, 28, 24, 63);


    /**
     * 根据话单接听状态返回对应的中文描述
     *
     * @param status 接听状态
     * @return
     */
    public static String getStatus(Integer status) {

        String result = "";
        if (!Objects.isNull(status)) {
            switch (status) {
                case CdrStatus.IB_BRIDGED:
                    result = "座席接听";
                    break;
                case CdrStatus.IB_CALLED:
                    result = "座席未接听";
                    break;
                case CdrStatus.IB_SYSTEM_ANSWER:
                    result = "系统接听";
                    break;
                case CdrStatus.IB_BAD_IVR:
                    result = "系统未接-IVR配置错误";
                    break;
                case CdrStatus.IB_ENTERPRISE_STOP:
                    result = "系统未接-停机";
                    break;
                case CdrStatus.IB_ENTERPRISE_PAUSED:
                    result = "系统未接-欠费";
                    break;
                case CdrStatus.IB_BLACK:
                    result = "系统未接-黑名单";
                    break;
                case CdrStatus.IB_ENTERPRISE_NO_REG:
                    result = "系统未接-未注册";
                    break;
                case CdrStatus.IB_NOANSWER_COLORRING:
                    result = "系统未接-彩铃";
                    break;
                case CdrStatus.IB_NO_ANSWER_WEB400_UNACCEPT:
                    result = "网上400未接受";
                    break;
                case CdrStatus.IB_OVER_LIMIT:
                    result = "呼叫超出营帐中设置的最大限制";
                    break;
                case CdrStatus.IB_SYSTEM_NOANSWER:
                    result = "客户呼入系统后在系统未应答前挂机";
                    break;
                case CdrStatus.IB_FAILED_OTHER:
                    result = "其他错误";
                    break;
                case CdrStatus.OB_WEBCALL_CUSTOMER_TTS_FAIL:
                    result = "webcall客户TTS失败";
                    break;
                case CdrStatus.OB_WEBCALL_ANSWER:
                    result = "webcall客户接听";
                    break;

                case CdrStatus.OB_INTERNAL_AGENT_ANSWER:
                    result = "内部呼叫座席接听";
                    break;
                case CdrStatus.OB_PREVIEW_AGENT_ANSWER:
                case CdrStatus.OB_PREDICTIVE_CUSTOMER_NO_ANSWER:
                case CdrStatus.OB_DIRECT_ANSWER:
                case CdrStatus.OB_DIRECT_CALLED:
                case CdrStatus.OB_PREVIEW_CALLED:
                case CdrStatus.OB_WEBCALL_NOANSWER:
                case CdrStatus.OB_INTERNAL_CALLED:
                case CdrStatus.OB_CUSTOMER_NO_ANSWER:
                case CdrStatus.OB_CUSTOMER_CONGESTION:
                case CdrStatus.OB_DIRECT_CUSTOMER_NO_ANSWER:
                    result = "客户未接听";
                    break;
                case CdrStatus.OB_PREVIEW_AGENT_NO_ANSWER:
                case CdrStatus.OB_PREDICTIVE_CALLED:
                case CdrStatus.OB_PREDICTIVE_ANSWER:
                case CdrStatus.OB_WEBCALL_CALLED:
                case CdrStatus.OB_INTERNAL_AGENT_NO_ANSWER:
                    result = "座席未接听";
                    break;
                case CdrStatus.OB_PREVIEW_BRIDGED:
                case CdrStatus.OB_PREDICTIVE_BRIDGED:
                case CdrStatus.OB_DIRECT_BRIDGED:
                case CdrStatus.OB_WEBCALL_BRIDGED:
                case CdrStatus.OB_INTERNAL_BRIDGED:
                case CdrStatus.OB_BRIDGED:
                    result = "双方接听";
                    break;
                default:
            }
        }
        return result;
    }

    /**
     * 对话单状态进行归类返回与通话记录中相对应的中文描述
     *
     * @param status 接听状态
     * @return
     */
    public static String conversionStatus(Integer status) {

        String result = "";
        if (!Objects.isNull(status)) {
            if (IB_ANSWER.contains(status)) {
                result = "人工接听";
            }
            if (IB_NO_ANSWER.contains(status)) {
                result = "人工未接听";
            }
            if (IB_SYSTEM_ANSWER.contains(status)) {
                result = "系统应答";
            }
            if (IB_SYSTEM_NO_ANSWER.contains(status)) {
                result = "系统未应答";
            }
            if (OB_CUSTOMER_NO_ANSWER.contains(status)) {
                result = "客户未接听";
            }
            if (OB_AGENT_NO_ANSWER.contains(status)) {
                result = "座席未接听";
            }
            if (OB_BRIDGED.contains(status)) {
                result = "双方接听";
            }
        }
        return result;
    }

    public static String getCallType(Integer type) {
        String result = "";
        if (Objects.nonNull(type)) {
            switch (type) {
                case CallType.IB:
                    result = "呼入";
                    break;
                case CallType.OB_WEBCALL:
                    result = "webCall";
                    break;
                case CallType.OB:
                    result = "点击外呼";
                    break;
                case CallType.PREVIEW_OB:
                    result = "预览外呼";
                    break;
                case CallType.PREDICTIVE_OB:
                    result = "预测外呼";
                    break;
                case CallType.DIRECT_OB:
                    result = "直接外呼";
                    break;
                case CallType.SELF_RECORD_OB:
                    result = "自助录音";
                    break;
                case CallType.SEND_FAX_OB:
                    result = "发送传真";
                    break;
                case CallType.INTERNAL_CALL:
                    result = "内部呼叫";
                    break;
                case CallType.ORDER_CALL_BACK:
                    result = "预约回呼";
                    break;

                case CallType.OB_CALL_CLIENT:
                case CallType.IB_CALL_CLIENT:
                    result = "呼转座席";
                    break;
                case CallType.IB_TRANSFER:
                    result = "转移";
                    break;
                case CallType.IB_CONSULT:
                    result = "咨询";
                    break;
                case CallType.IB_THREEWAY:
                    result = "三方";
                    break;
                case CallType.IB_SPY:
                    result = "监听";
                    break;
                case CallType.IB_WHISPER:
                    result = "耳语";
                    break;
                case CallType.IB_BARGE:
                    result = "强插";
                    break;
                case CallType.IB_PICKUP:
                    result = "抢线";
                    break;
                case CallType.OB_TRANSFER:
                    result = "转移";
                    break;
                case CallType.OB_CONSULT:
                    result = "咨询";
                    break;
                case CallType.OB_THREEWAY:
                    result = "三方";
                    break;
                case CallType.OB_SPY:
                    result = "监听";
                    break;
                case CallType.OB_WHISPER:
                    result = "耳语";
                    break;
                case CallType.OB_BARGE:
                    result = "强插";
                    break;
                case CallType.CALL_CUSTOMER:
                    result = "呼转客户";
                    break;
                case CallType.OB_CALL_EXTEN:
                    result = "呼转分机";
                    break;
                case CallType.OB_CALL_TEL:
                case CallType.IB_CALL_TEL:
                    result = "呼转电话";
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    /**
     * 标记值转换中文
     *
     * @param mark
     * @return
     */
    public static String getMark(Integer mark) {
        String result = "";
        if (Objects.nonNull(mark)) {
            switch (mark) {
                case 1:
                    result = "留言";
                    break;
                case 2:
                    result = "转移";
                    break;
                case 3:
                    result = "咨询";
                    break;
                case 4:
                    result = "三方";
                    break;
                case 5:
                    result = "传真接收";
                    break;
                case 6:
                    result = "会议";
                    break;
                case 7:
                    result = "交互";
                    break;
                case 8:
                    result = "IVR中放弃";
                    break;
                case 9:
                    result = "已进入IVR";
                    break;
                case 10:
                    result = "未进入IVR";
                    break;
                case 11:
                    result = "队列中放弃";
                    break;
                case 12:
                    result = "队列中溢出";
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    /**
     * 置忙类型
     *
     * @param pauseType
     * @return
     */
    public static String getPauseType(Integer pauseType) {
        String result = "";
        if (Objects.nonNull(pauseType)) {
            switch (pauseType) {
                case 1:
                    result = "普通";
                    break;
                case 2:
                    result = "休息";
                    break;
                case 3:
                    result = "IM";
                    break;
                case 4:
                    result = "强制";
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    public static String getSipCause(Integer sip) {
        if (!Objects.isNull(sip)) {
            SipCauseEnum sipCauseEnum = SipCauseEnum.desc(sip);
            if (Objects.nonNull(sipCauseEnum)) {
                return sipCauseEnum.desc();
            }
        }
        return "";
    }

    public static String getEndReason(Integer reason) {
        String str = "";
        if (!Objects.isNull(reason)) {
            switch (reason) {
                case 1000:
                    str = "主通道挂机";
                    break;
                case 1001:
                    str = "非主通道挂机";
                    break;
                case 1002:
                    str = "被强拆";
                    break;
                default:
            }
        }
        return str;
    }

    public static String getLoginStatus(Integer loginStatus) {
        String str = "";
        if (!Objects.isNull(loginStatus)) {
            switch (loginStatus) {
                case 0:
                    str = "离线";
                    break;
                case 1:
                    str = "在线空闲";
                    break;
                case 2:
                    str = "在线置忙";
                    break;
                case 3:
                    str = "在线整理";
                    break;
                default:
            }
        }
        return str;
    }

    public static String getDeviceStatus(Integer deviceStatus) {
        String str = "";
        if (!Objects.isNull(deviceStatus)) {
            switch (deviceStatus) {
                case 0:
                    str = "空闲";
                    break;
                case 1:
                    str = "锁定，锁定将要使用的设备";
                    break;
                case 2:
                    str = "请求，尝试呼叫设备";
                    break;
                case 3:
                    str = "响铃，设备正在响铃";
                    break;
                case 4:
                    str = "使用中，设备正在通话中";
                    break;
                case 9:
                    str = "离线";
                    break;
                default:
            }
        }
        return str;
    }

}
