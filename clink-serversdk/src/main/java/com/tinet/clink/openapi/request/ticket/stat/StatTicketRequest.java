package com.tinet.clink.openapi.request.ticket.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.stat.AbstractStatRequest;
import com.tinet.clink.openapi.response.ticket.stat.StatTicketResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * 工单整体统计报表请求
 *
 * @author wangli
 * @date 2022-08-24 10:38 上午
 */
public class StatTicketRequest extends AbstractStatRequest<StatTicketResponse> {

    /**
     * 标签
     */
    private String tag;

    /**
     * 来源渠道
     */
    private Integer[] sources;

    /**
     * 统计方式
     */
    private Integer statisticMethod;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
        if (Objects.nonNull(tag)) {
            putBodyParameter("tag", tag);
        }
    }

    public Integer[] getSources() {
        return sources;
    }

    public void setSources(Integer[] sources) {
        this.sources = sources;
        if (Objects.nonNull(sources)) {
            putBodyParameter("sources", sources);
        }
    }

    public Integer getStatisticMethod() {
        return statisticMethod;
    }

    public void setStatisticMethod(Integer statisticMethod) {
        this.statisticMethod = statisticMethod;
        if (!Objects.equals(statisticMethod, 1) && !Objects.equals(statisticMethod, 2)) {
            throw new IllegalArgumentException("statisticMethod must be 1 or 2!");
        }
        putBodyParameter("statisticMethod", statisticMethod);
    }

    @Override
    public Class<StatTicketResponse> getResponseClass() {
        return StatTicketResponse.class;
    }

    public StatTicketRequest() {
        super(PathEnum.StatTicket.value(), HttpMethodType.POST);
    }

}
