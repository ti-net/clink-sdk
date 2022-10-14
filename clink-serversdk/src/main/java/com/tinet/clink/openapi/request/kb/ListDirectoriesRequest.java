package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.ListDirectoriesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 知识库目录列表请求
 *
 * @author feizq
 * @date 2021/06/25
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListDirectoriesRequest extends AbstractRequestModel<ListDirectoriesResponse> {

    /**
     * 知识库名称
     */
    private String name;

    /**
     * 知识库类型 ，0 问答库 1 文档库
     */
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            putQueryParameter("name", name);
        }
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

    public ListDirectoriesRequest(){
        super(PathEnum.ListDirectories.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListDirectoriesResponse> getResponseClass() {
        return ListDirectoriesResponse.class;
    }
}
