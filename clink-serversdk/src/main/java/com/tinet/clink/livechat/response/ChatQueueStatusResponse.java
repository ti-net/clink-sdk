package com.tinet.clink.livechat.response;

import com.tinet.clink.livechat.model.ChatQueueStatus;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * @author dengsx
 * @create 2022/01/17
 **/
public class ChatQueueStatusResponse extends ResponseModel {
    private List<ChatQueueStatus> data;

    public List<ChatQueueStatus> getData() {
        return data;
    }

    public void setData(List<ChatQueueStatus> data) {
        this.data = data;
    }
}
