package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.huanxin.model.CreateAgentModel;

public class CreateAgentResponse extends ResponseModel {

    private CreateAgentModel data;

    public CreateAgentModel getCreateAgentModel() {
        return data;
    }

    public void setCreateAgentModel(CreateAgentModel createAgentModel) {
        this.data = createAgentModel;
    }
}
