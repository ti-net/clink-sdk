package com.tinet.clink.openapi.response.config.tel.restrict;

import com.tinet.clink.openapi.model.TelRestrictSettingSearchModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2021-12-13 2:05 下午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeTelRestrictSettingResponse extends ResponseModel {

    private TelRestrictSettingSearchModel setting;

    public TelRestrictSettingSearchModel getSetting() {
        return setting;
    }

    public void setSetting(TelRestrictSettingSearchModel setting) {
        this.setting = setting;
    }
}
