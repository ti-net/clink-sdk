package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.model.ChatWindowsSetting;
import com.tinet.clink.livechat.response.ChatClientEffortResponse;
import com.tinet.clink.livechat.response.ChatWebSettingResponse;
import com.tinet.clink.ticket.request.stat.AbstractStatRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author midong
 * @since 2023/4/24 19:39
 */
public class ChatWebSettingRequest extends AbstractStatRequest<ChatWebSettingResponse> {

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

    public ChatWebSettingRequest() {
        super(PathEnum.ChatWebSetting.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ChatWebSettingResponse> getResponseClass() {
        return ChatWebSettingResponse.class;
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
