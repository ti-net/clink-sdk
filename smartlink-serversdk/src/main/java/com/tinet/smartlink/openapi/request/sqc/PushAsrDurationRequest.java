package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.PushAsrDurationResponse;

import java.util.Date;

/**
 * BigBoss对接慧智 日流量推送 asr统计每日每个用户的流量推送至openApi
 *
 * @author liuhy
 * @date 2019/10/23
 */
public class PushAsrDurationRequest extends BaseRequest<PushAsrDurationResponse> {

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
     * ASR转写引擎
     */
    private String provider;
    /**
     * ASR转写计费时长，单位：秒
     */
    private Integer duration;

    /**
     * 计费时长（分钟）：不满一分钟按照一分钟去算。取模加一
     */
    private Integer billingDuration;

    /**
     * 流量统计日期 格式 yyyy-MM-dd
     */
    private Date date;


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

    public Integer getBillingDuration() {
        return billingDuration;
    }

    public void setBillingDuration(Integer billingDuration) {
        this.billingDuration = billingDuration;
        if (billingDuration != null) {
            putBodyParameter("billingDuration", billingDuration);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        if (date != null) {
            putBodyParameter("date", date);
        }
    }


    public PushAsrDurationRequest() {
        super("/sqc/bigBoss/asrDuration", HttpMethodType.POST);
    }

    @Override
    public Class<PushAsrDurationResponse> getResponseClass() {
        return PushAsrDurationResponse.class;
    }
}
