package com.tinet.clink.openapi.enums;

import java.util.Objects;

/**
 * SipCause枚举 不要问我为什么这么命名，不理解的可以去看clink CdrSqlUtil类第284行代码
 *
 * @author wangll
 * @date 2018/05/14
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public enum SipCauseEnum {


    // 7xx 自定义
    CAUSE_710(710, "占线/拒接"),
    CAUSE_711(711, "超时"),

    CAUSE_712(712, "占线/拒接"),

    CAUSE_713(713, "空号"),

    CAUSE_714(714, "关机"),

    CAUSE_715(715, "暂时无法接听"),

    CAUSE_716(716, "停机"),

    //6xx 全局错误类
    CAUSE_600(600, "忙"),

    CAUSE_603(603, "拒绝"),

    CAUSE_604(604, "不存在"),

    CAUSE_606(606, "不可接收"),

    //5xx 服务器失败类

    CAUSE_500(500, "未接通"),

    CAUSE_501(501, "没有实现"),

    CAUSE_502(502, "错误网关"),

    CAUSE_503(503, "服务不可获得"),

    CAUSE_504(504, "服务器超时"),

    CAUSE_505(505, "版本不支持"),

    CAUSE_506(506, "消息太长"),

    //4xx 客户端请求失败类

    CAUSE_400(400, "非法请求"),

    CAUSE_401(401, "用户代理请求认证"),

    CAUSE_402(402, "需要付费"),

    CAUSE_403(403, "禁止访问"),

    CAUSE_404(404, "空号"),

    CAUSE_405(405, "方法不允许"),

    CAUSE_406(406, "不接收"),

    CAUSE_407(407, "代理服务器要求认证"),

    CAUSE_408(408, "请求超时"),

    CAUSE_410(410, "消失"),

    CAUSE_413(413, "请求实体过大"),

    CAUSE_414(414, "请求URL太大"),

    CAUSE_415(415, "不支持的媒体类型"),

    CAUSE_416(416, "不支持的URL方案"),

    CAUSE_420(420, "错误扩展"),

    CAUSE_421(421, "需要扩展"),

    CAUSE_423(423, "间隔太短"),

    CAUSE_480(480, "暂时不可用"),

    CAUSE_481(481, "呼叫/事务不存在"),

    CAUSE_482(482, "检测到循环"),

    CAUSE_483(483, "跳数太多"),

    CAUSE_484(484, "地址不完整"),

    CAUSE_485(485, "不明确性"),

    CAUSE_486(486, "占线"),

    CAUSE_487(487, "请求终止"),

    CAUSE_488(488, "此时不可接收"),

    CAUSE_491(491, "请求悬而未决"),

    //3xx 转发请求类
    CAUSE_300(300, "多方选择"),

    CAUSE_301(301, "永久转移"),

    CAUSE_302(302, "临时转移"),

    CAUSE_305(305, "用户代理服务器"),

    CAUSE_380(380, "替代服务"),

    //2xx 成功信息
    CAUSE_200(200, "处理成功"),

    //1xx
    CAUSE_100(100, "待处理"),

    CAUSE_180(180, "回铃"),

    CAUSE_181(181, "呼叫被转发"),

    CAUSE_182(182, "排队"),
    CAUSE_183(183, "彩铃");

    private Integer code;
    private String desc;

    SipCauseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static SipCauseEnum desc(Integer code) {

        if (!Objects.equals(code, null)) {

            for (SipCauseEnum sipCauseEnum : SipCauseEnum.values()) {

                if (Objects.equals(code, sipCauseEnum.code)) {
                    return sipCauseEnum;
                }
            }
        }

        return null;
    }

    public static String getFieldDesc(Integer code) {

        SipCauseEnum sipCause = desc(code);

        return sipCause != null ? sipCause.desc : null;
    }

    public Integer code() {
        return code;
    }

    public String desc() {
        return desc;
    }
}

