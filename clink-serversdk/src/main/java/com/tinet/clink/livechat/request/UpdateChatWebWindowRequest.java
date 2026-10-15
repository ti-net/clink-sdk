package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.model.ChatWindowsSetting;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.UpdateChatWebWindowResponse;

/**
 * 只更新网页窗口，不影响七块和 ivrId
 */
public class UpdateChatWebWindowRequest extends AbstractStatRequest<UpdateChatWebWindowResponse> {

    private String accessId;
    private String accessName;
    private ChatWindowsSetting windowSetting;

    public UpdateChatWebWindowRequest() {
        super(PathEnum.UpdateChatWebWindow.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateChatWebWindowResponse> getResponseClass() {
        return UpdateChatWebWindowResponse.class;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public ChatWindowsSetting getWindowSetting() {
        return windowSetting;
    }

    public void setWindowSetting(ChatWindowsSetting windowSetting) {
        this.windowSetting = windowSetting;
    }
}
