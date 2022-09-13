package com.tinet.clink.core.constant;

/**
 * cdr状态
 *
 * @author libin
 * @date 2022/02/24
 */
public class CdrStatus {
    /**
     * 座席接听
     */
    public static final int IB_BRIDGED = 1;
    /**
     * 座席未接听
     */
    public static final int IB_CALLED = 2;
    /**
     * 系统接听
     */
    public static final int IB_SYSTEM_ANSWER = 3;
    /**
     * 系统未接-IVR配置错误
     */
    public static final int IB_BAD_IVR = 4;
    /**
     * 系统未接-停机
     */
    public static final int IB_ENTERPRISE_STOP = 5;
    /**
     * 系统未接-欠费
     */
    public static final int IB_ENTERPRISE_PAUSED = 6;
    /**
     * 黑名单
     */
    public static final int IB_BLACK = 7;
    /**
     * 系统未接-未注册
     */
    public static final int IB_ENTERPRISE_NO_REG = 8;
    /**
     * 系统未接-彩铃
     */
    public static final int IB_NOANSWER_COLORRING = 9;
    /**
     * 网上400未接受
     */
    public static final int IB_NO_ANSWER_WEB400_UNACCEPT = 10;
    /**
     * 呼叫超出营帐中设置的最大限制
     */
    public static final int IB_OVER_LIMIT = 11;
    /**
     * 系统未接
     */
    public static final int IB_SYSTEM_NOANSWER = 12;
    /**
     * 其他错误
     */
    public static final int IB_FAILED_OTHER = 13;
    /**
     * webcall客户TTS失败
     */
    public static final int OB_WEBCALL_CUSTOMER_TTS_FAIL = 20;
    /**
     * webcall 客户未接听(超时)
     */
    public static final int OB_WEBCALL_NOANSWER = 21;
    /**
     * webcall 客户接听
     */
    public static final int OB_WEBCALL_ANSWER = 22;
    /**
     * webcall 客户接听，座席未接听
     */
    public static final int OB_WEBCALL_CALLED = 23;
    /**
     * webcall 双方接听
     */
    public static final int OB_WEBCALL_BRIDGED = 24;
    /**
     * （预测外呼时）客户未接听(超时)
     */
    public static final int OB_CUSTOMER_NO_ANSWER = 25;
    /**
     * （预测外呼时）客户未接听(空号拥塞)
     */
    public static final int OB_CUSTOMER_CONGESTION = 26;
    /**
     * （直接外呼时）座席呼入后，呼叫客户未接听
     */
    public static final int OB_DIRECT_CUSTOMER_NO_ANSWER = 27;
    /**
     * 双方接听
     */
    public static final int OB_BRIDGED = 28;
    /**
     * 点击外呼座席未接听
     */
    public static final int OB_PREVIEW_AGENT_NO_ANSWER = 30;
    /**
     * 点击外呼座席接听
     */
    public static final int OB_PREVIEW_AGENT_ANSWER = 31;
    /**
     * 点击外呼客户未接听
     */
    public static final int OB_PREVIEW_CALLED = 32;
    /**
     * 点击外呼双方接听
     */
    public static final int OB_PREVIEW_BRIDGED = 33;

    /**
     * 自动外呼客户未接听
     */
    public static final int OB_PREDICTIVE_CUSTOMER_NO_ANSWER = 40;
    /**
     * 自动外呼客户接听
     */
    public static final int OB_PREDICTIVE_ANSWER = 41;
    /**
     * 自动外呼座席未接听
     */
    public static final int OB_PREDICTIVE_CALLED = 42;
    /**
     * 自动外呼双方接听
     */
    public static final int OB_PREDICTIVE_BRIDGED = 43;

    /**
     * 实体话机按键外呼，也称直接外呼
     */
    public static final int OB_DIRECT_ANSWER = 50;
    public static final int OB_DIRECT_CALLED = 51;
    public static final int OB_DIRECT_BRIDGED = 52;

    /**
     * 内部呼叫座席未接听
     */
    public static final int OB_INTERNAL_AGENT_NO_ANSWER = 60;
    /**
     * 内部呼叫座席接听
     */
    public static final int OB_INTERNAL_AGENT_ANSWER = 61;
    /**
     * 内部呼叫客户未接听
     */
    public static final int OB_INTERNAL_CALLED = 62;
    /**
     * 内部呼叫双方接听
     */
    public static final int OB_INTERNAL_BRIDGED = 63;
    /**
     * 2019-11-29 diaplan新增状态：座席响应未桥接
     * 详见：dialplan.ael 42行附近逻辑
     */
    public static final int DETAIL_ANSWER_NO_BRIDGED = 0;
    /**
     * 座席未接听
     */
    public static final int DETAIL_CALL_FAIL = 1;
    /**
     * 座席接听
     */
    public static final int DETAIL_ANSWER = 2;

}
