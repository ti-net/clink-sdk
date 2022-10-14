package com.tinet.clink.openapi.model;

/**
 * web返回对象
 *
 * @author wangll
 * @date 2020/04/02
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  WebcallResultModel {

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
