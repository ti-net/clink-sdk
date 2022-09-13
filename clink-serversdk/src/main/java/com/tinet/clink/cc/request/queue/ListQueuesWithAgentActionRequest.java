package com.tinet.clink.cc.request.queue;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.queue.ListQueuesWithAgentActionResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


public class ListQueuesWithAgentActionRequest extends AbstractRequestModel<ListQueuesWithAgentActionResponse> {

	/**
	 * 座席号
	 */
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

	public ListQueuesWithAgentActionRequest() {
		super(PathEnum.ListQueuesWithAgentAction.value(), HttpMethodType.GET);
	}

	@Override
	public Class<ListQueuesWithAgentActionResponse> getResponseClass() {
		return ListQueuesWithAgentActionResponse.class;
	}
}
