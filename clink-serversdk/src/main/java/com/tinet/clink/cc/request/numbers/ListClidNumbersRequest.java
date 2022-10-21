package com.tinet.clink.cc.request.numbers;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.numbers.ListClidNumbersResponse;
import com.tinet.clink.core.request.AbstractRequestModel;


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