package com.tinet.clink.openapi.request.config.queue;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.queue.ListQueuesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询队列列表
 *
 * @author lizy
 * @date 2018/10/25
 */
public class ListQueuesRequest extends AbstractRequestModel<ListQueuesResponse> {

    private Integer limit;

    private Integer offset;


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

    public ListQueuesRequest() {
        super(PathEnum.ListQueues.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListQueuesResponse> getResponseClass() {
        return ListQueuesResponse.class;
    }
}
