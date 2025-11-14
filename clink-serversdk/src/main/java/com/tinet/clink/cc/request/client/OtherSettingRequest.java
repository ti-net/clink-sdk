package com.tinet.clink.cc.request.client;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.client.OtherSettingResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

public class OtherSettingRequest extends AbstractRequestModel<OtherSettingResponse> {

	private String cno;

	private Integer agcRx;

	private Integer agcTx;

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
		if (cno != null) {
			putBodyParameter("cno", cno);
		}
	}

	public Integer getAgcRx() {
		return agcRx;
	}

	public void setAgcRx(Integer agcRx) {
		this.agcRx = agcRx;
		if (agcRx != null) {
			putBodyParameter("agcRx", agcRx);
		}
	}

	public Integer getAgcTx() {
		return agcTx;
	}

	public void setAgcTx(Integer agcTx) {
		this.agcTx = agcTx;
		if (agcTx != null) {
			putBodyParameter("agcTx", agcTx);
		}
	}

	public OtherSettingRequest() {
		super(PathEnum.OtherSetting.value(), HttpMethodType.POST);
	}

	@Override
	public Class<OtherSettingResponse> getResponseClass() {
		return OtherSettingResponse.class;
	}

}
