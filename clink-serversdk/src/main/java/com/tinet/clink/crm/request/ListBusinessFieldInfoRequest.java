package com.tinet.clink.crm.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.response.ListBusinessFieldInfoResponse;

/**
 * @author haoy
 * @date 2023/08/14
 */
public class ListBusinessFieldInfoRequest extends AbstractRequestModel<ListBusinessFieldInfoResponse> {

    public ListBusinessFieldInfoRequest() {
        super(PathEnum.ListBusinessFieldInfo.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListBusinessFieldInfoResponse> getResponseClass() {
        return ListBusinessFieldInfoResponse.class;
    }
}
