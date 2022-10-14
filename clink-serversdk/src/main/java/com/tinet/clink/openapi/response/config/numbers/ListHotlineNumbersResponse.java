package com.tinet.clink.openapi.response.config.numbers;

import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 查询热线号码集合响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListHotlineNumbersResponse extends ResponseModel {

    /**
     * 热线号码集合
     */
    private List<String> hotlines;

    public List<String> getHotlines() {
        return hotlines;
    }

    public void setHotlines(List<String> hotlines) {
        this.hotlines = hotlines;
    }
}