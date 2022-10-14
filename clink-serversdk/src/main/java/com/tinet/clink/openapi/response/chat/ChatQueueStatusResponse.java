package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.model.ChatQueueStatus;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author dengsx
 * @create 2022/01/17
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatQueueStatusResponse extends ResponseModel {
    private List<ChatQueueStatus> data;

    public List<ChatQueueStatus> getData() {
        return data;
    }

    public void setData(List<ChatQueueStatus> data) {
        this.data = data;
    }
}
