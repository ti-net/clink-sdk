package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.model.ChatWindowsSetting;
import com.tinet.clink.livechat.response.ChatWebSettingUpdateResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 网页渠道配置修改请求对象
 *
 * @author lj
 * @since 2023/9/24 19:39
 */
public class ChatWebSettingUpdateRequest extends AbstractStatRequest<ChatWebSettingUpdateResponse> {

    /**
     * 接入号名称
     */
    private String accessName;

    /**
     * 接入号id
     */
    private String accessId;

    /**
     * 窗口设置
     */
    private ChatWindowsSetting windowSetting;

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public ChatWindowsSetting getWindowSetting() {
        return windowSetting;
    }

    public void setWindowSetting(ChatWindowsSetting windowSetting) {
        this.windowSetting = windowSetting;
    }

    public ChatWebSettingUpdateRequest() {
        super(PathEnum.ChatWebSettingUpdate.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ChatWebSettingUpdateResponse> getResponseClass() {
        return ChatWebSettingUpdateResponse.class;
    }


    @Override
    public String toString() {
        return "ChatWebSettingRequest{" +
                "accessName='" + accessName + '\'' +
                ", accessId='" + accessId + '\'' +
                ", windowSetting=" + windowSetting +
                '}';
    }
}
