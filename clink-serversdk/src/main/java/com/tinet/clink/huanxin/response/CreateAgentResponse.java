package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.huanxin.model.CreateAgentModel;

public class CreateAgentResponse extends ResponseModel {

    private CreateAgentModel data;

    public CreateAgentModel getData() {
        return data;
    }

    public void setData(CreateAgentModel data) {
        this.data = data;
    }
}
