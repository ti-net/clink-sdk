package com.tinet.clink.openapi.utils;


import com.tinet.clink.openapi.constant.CallType;
import com.tinet.clink.openapi.constant.CdrStatus;

import java.util.Objects;


/**
 * @author libin
 * @date 2022/02/24
 **/
public class CommonUtils {

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
     * @param pauseType
     * @return
     */
    public static  String getPauseType(Integer pauseType) {
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

}
