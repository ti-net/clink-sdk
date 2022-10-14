package com.tinet.clink.openapi.response.config.tel.restrict;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.openapi.model.TelRestrictSettingUpdateModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-13 2:10 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  UpdateTelRestrictSettingResponse extends ResponseModel {

    private TelRestrictSettingUpdateModel setting;

    public TelRestrictSettingUpdateModel getSetting() {
        return setting;
    }

    public void setSetting(TelRestrictSettingUpdateModel setting) {
        this.setting = setting;
    }
}
