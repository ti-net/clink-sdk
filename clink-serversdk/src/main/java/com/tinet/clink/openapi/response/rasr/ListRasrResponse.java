package com.tinet.clink.openapi.response.rasr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.openapi.model.RasrDialog;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 新增黑白名单队列响应
 * @author libin
 * @date 2021-12-13 10:32 上午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListRasrResponse extends ResponseModel {

    private List<RasrDialog> botDialogs;

    public List<RasrDialog> getBotDialogs() {
        return botDialogs;
    }

    public void setBotDialogs(List<RasrDialog> botDialogs) {
        this.botDialogs = botDialogs;
    }
}
