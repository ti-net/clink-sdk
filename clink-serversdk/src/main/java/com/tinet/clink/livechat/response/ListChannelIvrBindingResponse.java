package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChannelIvrBindingPageModel;

/**
 * 查询渠道接入号与路由导航绑定响应
 */
public class ListChannelIvrBindingResponse extends ResponseModel {

    /**
     * 分页对象
     */
    private ChannelIvrBindingPageModel data;

    public ChannelIvrBindingPageModel getData() {
        return data;
    }

    public void setData(ChannelIvrBindingPageModel data) {
        this.data = data;
    }
}
