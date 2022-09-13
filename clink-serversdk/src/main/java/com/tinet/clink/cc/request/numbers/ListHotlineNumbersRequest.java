package com.tinet.clink.cc.request.numbers;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.numbers.ListHotlineNumbersResponse;
import com.tinet.clink.core.request.AbstractRequestModel;


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