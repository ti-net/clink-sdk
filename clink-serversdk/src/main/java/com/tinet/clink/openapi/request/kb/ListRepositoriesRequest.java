package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.ListRepositoriesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 知识库仓库列表请求
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class ListRepositoriesRequest extends AbstractRequestModel<ListRepositoriesResponse> {

    /**
     * 知识库类型
     */
    private Integer type;

    /**
     * 知识库名称
     */
    private String name;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            putQueryParameter("type", type);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            putQueryParameter("name", name);
        }
    }

    public ListRepositoriesRequest() {
        super(PathEnum.ListRepositories.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListRepositoriesResponse> getResponseClass() {
        return ListRepositoriesResponse.class;
    }
}
