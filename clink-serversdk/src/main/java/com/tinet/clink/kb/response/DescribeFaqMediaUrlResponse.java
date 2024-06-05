package com.tinet.clink.kb.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.kb.model.DescribeFileMediaUrlModel;
import com.tinet.clink.kb.model.SearchKnowledgeOnOpenModel;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class DescribeFaqMediaUrlResponse extends ResponseModel {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
