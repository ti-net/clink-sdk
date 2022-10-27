package com.tinet.clink.openapi.request.business;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.business.ListBusinessFieldResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询业务记录的自定义字段
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListBusinessFieldRequest extends AbstractRequestModel<ListBusinessFieldResponse> {

    public ListBusinessFieldRequest() {
        super(PathEnum.ListBusinessField.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListBusinessFieldResponse> getResponseClass() {
        return ListBusinessFieldResponse.class;
    }
}