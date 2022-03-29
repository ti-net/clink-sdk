package com.tinet.clink.openapi.constant;

/**
 * 呼叫类型常量类
 *
 * @author wangll
 * @date 2019/10/15
 */
public class CallType {

    /**
     * 呼入，客户侧为主通道
     */
    public static final int IB = 1;
    /**
     * 网上400呼入，客户侧为主通道
     */
    public static final int OB_WEBCALL = 2;
    /**
     * 点击外呼呼座席的通道，座席侧为主通道
     */
    public static final int OB = 3;
    /**
     * 预览外呼呼座席的通道，座席侧为主通道
     */
    public static final int PREVIEW_OB = 4;
    /**
     * 预测外呼呼客户的通道，客户侧为主通道
     */
    public static final int PREDICTIVE_OB = 5;
    /**
     * 直接外呼座席呼入的通道，座席侧为主通道
     */
    public static final int DIRECT_OB = 6;
    /**
     * 自助录音
     */
    public static final int SELF_RECORD_OB = 7;
    /**
     * 发送传真
     */
    public static final int SEND_FAX_OB = 8;
    /**
     * 内部呼叫
     */
    public static final int INTERNAL_CALL = 9;
    /**
     * 预约回呼
     */
    public static final int ORDER_CALL_BACK = 10;



    /**
     * 呼入后呼转座席
     */
    public static final int IB_CALL_CLIENT = 101;
    /**
     * 呼入的转移
     */
    public static final int IB_TRANSFER = 102;
    /**
     * 呼入的咨询
     */
    public static final int IB_CONSULT = 103;
    /**
     * 呼入的三方通话
     */
    public static final int IB_THREEWAY = 104;
    /**
     * 呼入的被监听
     */
    public static final int IB_SPY = 105;
    /**
     * 呼入的被耳语
     */
    public static final int IB_WHISPER = 106;
    /**
     * 呼入的被拦截
     */
    public static final int IB_BARGE = 107;
    /**
     * 呼入的被抢线
     */
    public static final int IB_PICKUP = 108;
    /**
     * 呼入后呼转到电话
     */
    public static final int IB_CALL_TEL = 109;

    /**
     * 预测外呼的呼座席
     */
    public static final int OB_CALL_CLIENT = 201;
    /**
     * 外呼的转移
     */
    public static final int OB_TRANSFER = 202;
    /**
     * 外呼的咨询
     */
    public static final int OB_CONSULT = 203;
    /**
     * 外呼的三方
     */
    public static final int OB_THREEWAY = 204;
    /**
     * 外呼的被监听
     */
    public static final int OB_SPY = 205;
    /**
     * 外呼的被三方通话
     */
    public static final int OB_WHISPER = 206;
    /**
     * 外呼的被拦截
     */
    public static final int OB_BARGE = 207;
    /**
     * 点击外呼，预览外呼，直接外呼的呼叫客户
     */
    public static final int CALL_CUSTOMER = 208;
    /**
     * 外呼的呼电话
     */
    public static final int OB_CALL_TEL = 209;
    /**
     * 外呼的后转接到分机
     */
    public static final int OB_CALL_EXTEN = 210;

    /**
     * 呼入类型 对应的所有CALL TYPE
     */
    public static final Integer[] IB_CALL_TYPE = {CallType.IB_CALL_CLIENT, CallType.IB_TRANSFER, CallType.IB_CONSULT,
            CallType.IB_THREEWAY, CallType.IB_SPY, CallType.IB_WHISPER, CallType.IB_BARGE, CallType.IB_PICKUP,
            CallType.IB_CALL_TEL, CallType.IB};
    /**
     * 呼出类型 对应的所有CALL TYPE
     */
    public static final Integer[] OB_CALL_TYPE = {CallType.OB_CALL_CLIENT, CallType.OB_TRANSFER, CallType.OB_CONSULT,
            CallType.OB_THREEWAY, CallType.OB_SPY, CallType.OB_WHISPER, CallType.OB_BARGE, CallType.CALL_CUSTOMER,
            CallType.OB_CALL_TEL, CallType.OB_CALL_EXTEN, CallType.OB, CallType.PREVIEW_OB, CallType.DIRECT_OB,
            CallType.INTERNAL_CALL, CallType.OB_WEBCALL, CallType.PREDICTIVE_OB};
}
