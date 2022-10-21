package com.tinet.clink.cc.request.ivr;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.ivr.ListIvrsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 查询语音导航列表请求
 *
 * @author huwk
 * @date 2018/11/15
 **/
public class ListIvrsRequest extends AbstractRequestModel<ListIvrsResponse> {

    public ListIvrsRequest() {
        super(PathEnum.ListIvrs.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListIvrsResponse> getResponseClass() {
        return ListIvrsResponse.class;
    }
}
