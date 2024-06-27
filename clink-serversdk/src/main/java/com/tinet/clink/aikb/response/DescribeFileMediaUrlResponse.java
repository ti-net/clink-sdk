package com.tinet.clink.aikb.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class DescribeFileMediaUrlResponse extends ResponseModel {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
