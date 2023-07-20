package com.tinet.clink.cc.request.exten;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.exten.ListBindedExtensResponse;
import com.tinet.clink.core.request.AbstractRequestModel;


/**
 * 查询被绑定的话机列表请求
 *
 * @author yinzk
 * @date 2023/07/14
 */
public class ListBindedExtensRequest extends AbstractRequestModel<ListBindedExtensResponse> {


    /**
     * 话机类型，1: 分机， 2: 软电话
     */
    private Integer type;
    /**
     * 偏移量，范围 0-10000，默认值为 0
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100，默认值为 10
     */
    private Integer limit;

    public ListBindedExtensRequest() {
        super(PathEnum.ListBindedExtens.value());
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            putQueryParameter("type", type);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    @Override
    public Class<ListBindedExtensResponse> getResponseClass() {
        return ListBindedExtensResponse.class;
    }
}