package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.ListChatHttpPushResponse;

/**
 * 分页查询数据推送
 */
public class ListChatHttpPushRequest extends AbstractStatRequest<ListChatHttpPushResponse> {

    /**
     * 推送类型
     */
    private Integer type;

    /**
     * 页码，默认 1
     */
    private Integer pageIndex;

    /**
     * 每页条数，默认 10
     */
    private Integer pageSize;

    public ListChatHttpPushRequest() {
        super(PathEnum.ListChatHttpPush.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListChatHttpPushResponse> getResponseClass() {
        return ListChatHttpPushResponse.class;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
