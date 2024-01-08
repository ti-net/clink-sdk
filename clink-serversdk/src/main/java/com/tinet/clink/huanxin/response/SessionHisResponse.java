package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.huanxin.model.SessionHisModel;

public class SessionHisResponse extends ResponseModel {

    private SessionHisModel data;

    public SessionHisModel getData() {
        return data;
    }

    public void setData(SessionHisModel data) {
        this.data = data;
    }
}
