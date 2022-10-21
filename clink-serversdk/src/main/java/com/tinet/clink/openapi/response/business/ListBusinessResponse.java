package com.tinet.clink.openapi.response.business;

import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**查询业务记录
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListBusinessResponse extends PagedResponse {

    /**
     * 客户资料列表 --Map中存放每一条客户资料的字段值
     */
    private List<Map<String, Object>> businesses;

    public List<Map<String, Object>> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Map<String, Object>> businesses) {
        this.businesses = businesses;
    }
}