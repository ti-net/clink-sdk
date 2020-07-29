package com.tinet.clink.openapi.request.business;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.BusinessSearchModel;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.Business.ListBusinessResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**业务记录查询
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
public class ListBusinessRequest extends AbstractRequestModel<ListBusinessResponse> {


    private BusinessSearchModel businessSearchModel;

    public BusinessSearchModel getBusinessSearchModel() {
        return businessSearchModel;
    }

    public void setBusinessSearchModel(BusinessSearchModel businessSearchModel) {
        this.businessSearchModel = businessSearchModel;
    }

    public ListBusinessRequest() {
        super(PathEnum.ListBusiness.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListBusinessResponse> getResponseClass() {
        return ListBusinessResponse.class;
    }
}