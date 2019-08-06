package com.tinet.clink.openapi.request.config.numbers;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.numbers.ListHotlineNumbersResponse;

/**
 * 查询热线号码集合请求
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class ListHotlineNumbersRequest extends AbstractRequestModel<ListHotlineNumbersResponse> {

    public ListHotlineNumbersRequest() {
        super(PathEnum.ListHotlineNumbers.value());
    }

    @Override
    public Class<ListHotlineNumbersResponse> getResponseClass() {
        return ListHotlineNumbersResponse.class;
    }
}