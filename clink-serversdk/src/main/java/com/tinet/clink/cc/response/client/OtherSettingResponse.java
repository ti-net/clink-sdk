package com.tinet.clink.cc.response.client;

import com.tinet.clink.cc.model.OtherSettingsDesModel;
import com.tinet.clink.core.response.ResponseModel;

public class OtherSettingResponse extends ResponseModel {

	OtherSettingsDesModel otherSettings;

	public OtherSettingsDesModel getOtherSettings() {
		return otherSettings;
	}

	public void setOtherSettings(OtherSettingsDesModel otherSettings) {
		this.otherSettings = otherSettings;
	}
}
