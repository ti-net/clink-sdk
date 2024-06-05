package com.tinet.clink.kb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DescribeFileMediaUrlModel {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
