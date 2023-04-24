package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import java.util.List;
import java.util.Map;

/**
 * 在线客服-座席工作量报表 Response
 * @author midong
 */
public class StatChatClientWorkQualityResponse extends ResponseModel {

    private List<Map<String, Object>> statChatClientWorkQuality;

    public List<Map<String, Object>> getStatChatClientWorkQuality() {
        return statChatClientWorkQuality;
    }

    public void setStatChatClientWorkQuality(List<Map<String, Object>> statChatClientWorkQuality) {
        this.statChatClientWorkQuality = statChatClientWorkQuality;
    }
}
