package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * 创建渠道接入号响应。channel 类型随渠道变化。
 */
public class CreateChannelAccessResponse extends ResponseModel {

    /**
     * 创建结果，类型随渠道变化
     */
    private Object channel;

    public Object getChannel() {
        return channel;
    }

    public void setChannel(Object channel) {
        this.channel = channel;
    }
}
