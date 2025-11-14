package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.huanxin.model.CreateQueueModel;

public class CreateQueueResponse extends ResponseModel {

   private CreateQueueModel data;

    public CreateQueueModel getData() {
        return data;
    }

    public void setData(CreateQueueModel data) {
        this.data = data;
    }
}
