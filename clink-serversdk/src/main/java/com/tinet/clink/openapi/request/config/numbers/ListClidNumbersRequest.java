package com.tinet.clink.openapi.request.config.numbers;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.numbers.ListClidNumbersResponse;

/**
 * 查询外显号码集合请求
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class ListClidNumbersRequest extends AbstractRequestModel<ListClidNumbersResponse> {

    public ListClidNumbersRequest() {
        super(PathEnum.ListClidNumbers.value());
    }

    @Override
    public Class<ListClidNumbersResponse> getResponseClass() {
        return ListClidNumbersResponse.class;
    }
}