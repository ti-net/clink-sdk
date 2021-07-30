package com.tinet.clink.openapi.response.stat;

import com.tinet.clink.openapi.response.PagedResponse;
import java.util.List;
import java.util.Map;

/**
 * @author lrf
 * @date 2021/7/20 15:44
 **/
public class StatIvrNodesResponse extends PagedResponse {

    private List<Map<String,Object>> statIvrNodes;

    public List<Map<String, Object>> getStatIvrNodes() {
        return statIvrNodes;
    }

    public void setStatIvrNodes(List<Map<String, Object>> statIvrNodes) {
        this.statIvrNodes = statIvrNodes;
    }
}
