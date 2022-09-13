package com.tinet.clink.crm.request;

import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.response.ListBusinessFieldResponse;

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