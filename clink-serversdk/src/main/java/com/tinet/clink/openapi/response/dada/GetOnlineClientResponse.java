package com.tinet.clink.openapi.response.dada;

import com.tinet.clink.openapi.model.dada.OnlineUserListModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;
import java.util.Map;

/**
 * 登录响应
 *
 * @author lizy
 * @date 2018/10/24
 */
public class GetOnlineClientResponse extends ResponseModel {

    private List<OnlineUserListModel> result;

    public List<OnlineUserListModel> getResult() {
        return result;
    }

    public void setResult(List<OnlineUserListModel> result) {
        this.result = result;
    }
}
