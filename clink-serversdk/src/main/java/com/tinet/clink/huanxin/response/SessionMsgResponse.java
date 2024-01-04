package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.huanxin.model.SessionHisModel;
import com.tinet.clink.huanxin.model.SessionMsgModel;

import java.util.List;

public class SessionMsgResponse extends PagedResponse {

    private List<SessionMsgModel> data;

    public List<SessionMsgModel> getData() {
        return data;
    }

    public void setData(List<SessionMsgModel> data) {
        this.data = data;
    }
}
