package com.tinet.clink.crm.response;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**查询业务记录
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
public class ListBusinessCountResponse extends PagedResponse {

    /**
     * 客户资料列表 --Map中存放每一条客户资料的字段值
     */
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}