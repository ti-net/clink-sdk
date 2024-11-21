package com.tinet.clink.cc.request.client;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.client.OtherSettingResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

public class ListOtherSettingRequest extends AbstractRequestModel<OtherSettingResponse> {

	private String cno;

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
		if (cno != null) {
			putQueryParameter("cno", cno);
		}
	}

	public ListOtherSettingRequest() {
		super(PathEnum.ListOtherSetting.value(), HttpMethodType.GET);
	}

	@Override
	public Class<OtherSettingResponse> getResponseClass() {
		return OtherSettingResponse.class;
	}

}
