package com.tinet.clink.cc.response.investigation;

import com.tinet.clink.openapi.model.InvestigationSettingModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author: wangpw
 * @date: 2022/3/28
 * @description:
 */
public class InvestigationSettingResponse extends ResponseModel {

    private InvestigationSettingModel setting;

    public InvestigationSettingModel getSetting() {
        return setting;
    }

    public void setSetting(InvestigationSettingModel setting) {
        this.setting = setting;
    }

    @Override
    public String toString() {
        return "InvestigationSettingResponse{" +
                "setting=" + setting +
                "} " + super.toString();
    }
}
