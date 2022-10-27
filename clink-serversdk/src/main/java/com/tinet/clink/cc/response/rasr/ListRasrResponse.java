package com.tinet.clink.cc.response.rasr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.cc.model.RasrDialogModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 新增黑白名单队列响应
 * @author libin
 * @date 2021-12-13 10:32 上午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListRasrResponse extends ResponseModel {

    private List<RasrDialogModel> botDialogs;

    public List<RasrDialogModel> getBotDialogs() {
        return botDialogs;
    }

    public void setBotDialogs(List<RasrDialogModel> botDialogs) {
        this.botDialogs = botDialogs;
    }
}
