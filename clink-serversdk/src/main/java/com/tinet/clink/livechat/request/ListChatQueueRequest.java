package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.ListChatQueueResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 只读队列列表
 */
public class ListChatQueueRequest extends AbstractStatRequest<ListChatQueueResponse> {

    public ListChatQueueRequest() {
        super(PathEnum.ListChatQueue.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListChatQueueResponse> getResponseClass() {
        return ListChatQueueResponse.class;
    }
}
