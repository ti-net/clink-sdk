package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushAsrResponse;

/**
 * BigBoss对接慧智平台asr计费 请求
 *
 * @author liuhy
 * @date 2019/10/15
 */
public class PushAsrRequest extends BaseRequest<PushAsrResponse> {

    /**
     * 账户登录名
     */
    private String accountLoginName;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 通话唯一标识
     */
    private String uniqueId;
    /**
     * 主通道通话唯一标识
     */
    private String mainUniqueId;

    /**
     * 话单文件类型
     */
    private String cdrType;
    /**
     * 录音来源类型、默认是类型 1
     * 1：平台自计费话单录音。（一般这种情况转写后不需要推送bigboss计费）
     * 2：质检平台第三方用户（90开头的企业）
     * 3：客户通过接口对接质检，（例如vnc的一些企业）
     * 4：页面上传录平台第三方客户话单音做质检的话单
     */
    private Integer cdrSource;
    /**
     * 呼叫类型
     */
    private String callType;
    /**
     * 坐席工号
     */
    private String cno;

    /**
     * ASR转写引擎
     */
    private String provider;
    /**
     * ASR转写计费时长，单位：秒
     */
    private Integer duration;
    /**
     * 发起转写时间，10位时间戳
     */
    private Long createTime;
    /**
     * 转写结束时间，10位时间戳
     */
    private Long finishTime;

    public Integer getCdrSource() {
        return cdrSource;
    }

    public void setCdrSource(Integer cdrSource) {
        this.cdrSource = cdrSource;
        if (cdrSource != null) {
            putBodyParameter("cdrSource", cdrSource);
        }
    }

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
        if (accountLoginName != null) {
            putBodyParameter("accountLoginName", accountLoginName);
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putBodyParameter("uniqueId", uniqueId);
        }
    }


    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putBodyParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getCdrType() {
        return cdrType;
    }

    public void setCdrType(String cdrType) {
        this.cdrType = cdrType;
        if (cdrType != null) {
            putBodyParameter("cdrType", cdrType);
        }
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
        if (callType != null) {
            putBodyParameter("callType", callType);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
        if (provider != null) {
            putBodyParameter("provider", provider);
        }
    }


    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
        if (duration != null) {
            putBodyParameter("duration", duration);
        }
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
        if (createTime != null) {
            putBodyParameter("createTime", createTime);
        }
    }


    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
        if (finishTime != null) {
            putBodyParameter("finishTime", finishTime);
        }
    }

    public PushAsrRequest() {
        super("/sqc/bigBoss", HttpMethodType.POST);
    }

    @Override
    public Class<PushAsrResponse> getResponseClass() {
        return PushAsrResponse.class;
    }
}
