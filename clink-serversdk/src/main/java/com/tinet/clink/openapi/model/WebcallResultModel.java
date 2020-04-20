package com.tinet.clink.openapi.model;

/**
 * web返回对象
 *
 * @author wangll
 * @date 2020/04/02
 */
public class WebcallResultModel {

    /**
     * 请求的唯一id
     */
    private String requestUniqueId;

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
    }
}
