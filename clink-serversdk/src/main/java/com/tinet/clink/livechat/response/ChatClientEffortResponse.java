package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.PagedResponse;
import java.util.List;
import java.util.Map;

/**
 * @author midong
 * @since 2023/4/24 19:40
 */
public class ChatClientEffortResponse extends PagedResponse {

    private List<Map<String, Object>> statChatClientEffort;

    public List<Map<String, Object>> getStatChatClientEffort() {
        return statChatClientEffort;
    }

    public void setStatChatClientEffort(List<Map<String, Object>> statChatClientEffort) {
        this.statChatClientEffort = statChatClientEffort;
    }

}
