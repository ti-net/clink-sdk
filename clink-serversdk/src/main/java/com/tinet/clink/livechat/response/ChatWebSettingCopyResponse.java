package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * 复制网页渠道相应对象
 *
 * @author lj
 * @since 2023/9/22 19:40
 */
public class ChatWebSettingCopyResponse extends ResponseModel {

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
}
