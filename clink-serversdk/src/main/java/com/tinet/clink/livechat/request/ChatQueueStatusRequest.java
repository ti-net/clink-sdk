package com.tinet.clink.livechat.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatQueueStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;

/**
 * 在线客服队列座席状态
 * @author dengsx
 * @create 2022/01/17
 **/
public class ChatQueueStatusRequest extends AbstractRequestModel<ChatQueueStatusResponse> {

    private List<String> qnos;

    public ChatQueueStatusRequest(){
        super(PathEnum.ChatQueueAgentStatus.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ChatQueueStatusResponse> getResponseClass() {
        return ChatQueueStatusResponse.class;
    }

    public List<String> getQnos() {
        return qnos;
    }

    public void setQnos(List<String> qnos) {
        this.qnos = qnos;
        StringBuilder queryString = new StringBuilder();
        if (qnos != null && !qnos.isEmpty()){
            for (String qno : qnos) {
                queryString.append(qno);
                queryString.append(",");
            }
        }
        this.putQueryParameter("qnos",queryString);
    }

    @Override
    public String toString() {
        return "ChatQueueAgentStatusRequest{" +
                "qnos=" + qnos +
                "} " + super.toString();
    }
}
