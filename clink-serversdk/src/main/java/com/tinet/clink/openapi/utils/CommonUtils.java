package com.tinet.clink.openapi.utils;


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

}
