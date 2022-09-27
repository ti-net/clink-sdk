package com.tinet.clink.openapi.request.config.queue;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.queue.ListQueuesWithAgentActionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListQueuesWithAgentActionRequest extends AbstractRequestModel<ListQueuesWithAgentActionResponse> {

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
