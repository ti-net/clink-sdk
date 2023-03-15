package com.tinet.clink.crm.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.BusinessSaveReturnModel;

/**
 * 创建业务记录返回详情
 *
 * @author gexd
 * @date 2023/03/08
 */
public class CreateBusinessResponse extends ResponseModel {

   private BusinessSaveReturnModel business;

    public BusinessSaveReturnModel getBusiness() {
        return business;
    }

    public void setBusiness(BusinessSaveReturnModel business) {
        this.business = business;
    }
}
