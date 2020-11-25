package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.stat.AbstractStatRequest;
import com.tinet.clink.openapi.response.chat.StatChatQueueWorkloadResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 在线客服-队列报表 Request
 *
 * @author ningkun
 * @date 2020/11/25
 */
public class StatChatQueueWorkloadRequest extends AbstractStatRequest<StatChatQueueWorkloadResponse> {


    private Integer statisticMethod;

    public Integer getStatisticMethod() {
        return statisticMethod;
    }

    public void setStatisticMethod(Integer statisticMethod) {
        this.statisticMethod = statisticMethod;
        if (statisticMethod != null && statisticMethod != 2 && statisticMethod != 3) {
            throw new IllegalArgumentException("statisticMethod must be between 2 and 3 !");
        }
        if (statisticMethod != null) {
            putQueryParameter("statisticMethod", statisticMethod);
        }
    }
    @Override
    public Class<StatChatQueueWorkloadResponse> getResponseClass() {
        return StatChatQueueWorkloadResponse.class;
    }

    public StatChatQueueWorkloadRequest() {
        super(PathEnum.StatChatQueueWorkload.value(), HttpMethodType.POST);
    }
}
