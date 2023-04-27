package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatClientEffortResponse;
import com.tinet.clink.ticket.request.stat.AbstractStatRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author midong
 * @since 2023/4/24 19:39
 */
public class ChatClientEffortRequest extends AbstractStatRequest<ChatClientEffortResponse> {

    /**
     * 起始日期
     */
    private String startTime;

    /**
     * 截止日期
     */
    private String endTime;

    /**
     * 默认正序
     */
    private boolean sortAsc;

    /**
     * 座席数组
     */
    private List<String> cnos;

    /**
     *  渠道类型
     */
    private List<Integer> appType;

    private Integer limit;

    private Integer offset;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
        }
    }

    public Boolean getSortAsc() {
        return sortAsc;
    }

    public void setSortAsc(Boolean sortAsc) {
        this.sortAsc = sortAsc;
        if (sortAsc != null) {
            putQueryParameter("sortAsc", sortAsc);
        }
    }

    public List<String> getCnos() {
        return cnos;
    }

    public void setCnos(List<String> cnos) {
        this.cnos = cnos;
        if (cnos != null && cnos.size() != 0) {
            putQueryParameter("cnos", convertStringListToString(cnos));
        }
    }


    public List<Integer> getAppType() {
        return appType;
    }

    public void setAppType(List<Integer> appType) {
        this.appType = appType;
        if (appType != null && appType.size() != 0) {
            putQueryParameter("appType", convertIntegerListToString(appType));
        }

    }
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public ChatClientEffortRequest() {
        super(PathEnum.StatChatClientEffort.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ChatClientEffortResponse> getResponseClass() {
        return ChatClientEffortResponse.class;
    }

    private static String convertStringListToString(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.stream().collect(Collectors.joining(","));
        }
    }

    private static String convertIntegerListToString(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.stream().map(Object::toString).collect(Collectors.joining(","));
        }
    }
}
