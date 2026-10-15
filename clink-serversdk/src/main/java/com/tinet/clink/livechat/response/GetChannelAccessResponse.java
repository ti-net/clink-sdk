package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChannelAccessModel;

/**
 * 查询渠道接入号响应
 */
public class GetChannelAccessResponse extends ResponseModel {

    /**
     * 接入号
     */
    private ChannelAccessModel channel;


    public ChannelAccessModel getChannel() {
        return channel;
    }

    public void setChannel(ChannelAccessModel channel) {
        this.channel = channel;
    }

}
