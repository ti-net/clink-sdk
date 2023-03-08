package com.tinet.clink.crm.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.BusinessDetailModel;

/**
 * 创建业务记录返回详情
 *
 * @author gexd
 * @date 2023/03/08
 */
public class CreateBusinessResponse extends ResponseModel {

   private BusinessDetailModel business;

    public BusinessDetailModel getBusiness() {
        return business;
    }

    public void setBusiness(BusinessDetailModel business) {
        this.business = business;
    }
}
