package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.model.ChatWindowsSetting;
import com.tinet.clink.livechat.response.ChatWebSettingCopyResponse;
import com.tinet.clink.livechat.response.ChatWebSettingUpdateResponse;
import com.tinet.clink.ticket.request.stat.AbstractStatRequest;

/**
 * 复制网页渠道
 *
 * @author lj
 * @since 2023/9/22 19:39
 */
public class ChatWebSettingCopyRequest extends AbstractStatRequest<ChatWebSettingCopyResponse> {
    /**
     * 接入号id
     */
    private String accessId;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public ChatWebSettingCopyRequest() {
        super(PathEnum.ChatWebSettingCopy.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ChatWebSettingCopyResponse> getResponseClass() {
        return ChatWebSettingCopyResponse.class;
    }


    @Override
    public String toString() {
        return "ChatWebSettingCopyRequest{" +
                "accessId='" + accessId + '\'' +
                '}';
    }
}
