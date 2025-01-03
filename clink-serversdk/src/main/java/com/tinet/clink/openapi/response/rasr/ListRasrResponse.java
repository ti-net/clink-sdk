package com.tinet.clink.openapi.response.rasr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.openapi.model.RasrDialogModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 新增黑白名单队列响应
 * @author libin
 * @date 2021-12-13 10:32 上午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListRasrResponse extends ResponseModel {

    private List<RasrDialogModel> botDialogs;

    public List<RasrDialogModel> getBotDialogs() {
        return botDialogs;
    }

    public void setBotDialogs(List<RasrDialogModel> botDialogs) {
        this.botDialogs = botDialogs;
    }
}
