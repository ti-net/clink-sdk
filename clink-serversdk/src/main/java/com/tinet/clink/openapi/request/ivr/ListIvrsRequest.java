package com.tinet.clink.openapi.request.ivr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ivr.ListIvrsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询语音导航列表请求
 *
 * @author huwk
 * @date 2018/11/15
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListIvrsRequest extends AbstractRequestModel<ListIvrsResponse> {

    public ListIvrsRequest() {
        super(PathEnum.ListIvrs.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListIvrsResponse> getResponseClass() {
        return ListIvrsResponse.class;
    }
}
