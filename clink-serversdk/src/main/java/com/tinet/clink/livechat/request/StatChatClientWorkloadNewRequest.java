package com.tinet.clink.livechat.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.stat.AbstractStatRequest;
import com.tinet.clink.openapi.response.chat.StatChatClientWorkloadResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 在线客服-座席工作量报表（新） Request
 *
 * @author ningkun
 * @date 2020/11/25
 */
public class StatChatClientWorkloadNewRequest extends AbstractStatRequest<StatChatClientWorkloadResponse> {

    /**
     * 统计方式 2-汇总 3-分时累计
     */
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
    public Class<StatChatClientWorkloadResponse> getResponseClass() {
        return StatChatClientWorkloadResponse.class;
    }

    public StatChatClientWorkloadNewRequest() {
        super(PathEnum.StatChatClientWorkloadNew.value(), HttpMethodType.POST);
    }

}
