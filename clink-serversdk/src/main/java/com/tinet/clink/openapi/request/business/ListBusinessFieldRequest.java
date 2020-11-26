package com.tinet.clink.openapi.request.business;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.Business.ListBusinessFieldResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询业务记录的自定义字段
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
public class ListBusinessFieldRequest extends AbstractRequestModel<ListBusinessFieldResponse> {

    public ListBusinessFieldRequest() {
        super(PathEnum.ListBusinessField.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListBusinessFieldResponse> getResponseClass() {
        return ListBusinessFieldResponse.class;
    }
}