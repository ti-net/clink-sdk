package com.tinet.clink.openapi.utils;

/**
 * @author houfc
 */
public enum HttpMethodType {
    GET(false),
    POST(true),
    PUT(true),
    DELETE(false),
    HEAD(false),
    OPTIONS(false);

    private boolean hasContent;

    HttpMethodType(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public boolean hasContent() {
        return hasContent;
    }
}
