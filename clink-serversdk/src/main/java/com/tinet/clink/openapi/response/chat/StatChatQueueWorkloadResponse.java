package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;
import java.util.Map;

/**
 * 在线客服-队列报表 Response
 *
 * @author ningkun
 * @date 2020/11/25
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  StatChatQueueWorkloadResponse extends ResponseModel {

    private List<Map<String, Object>> statChatQueueWorkload;

    public List<Map<String, Object>> getStatChatQueueWorkload() {
        return statChatQueueWorkload;
    }

    public void setStatChatQueueWorkload(List<Map<String, Object>> statChatQueueWorkload) {
        this.statChatQueueWorkload = statChatQueueWorkload;
    }
}
