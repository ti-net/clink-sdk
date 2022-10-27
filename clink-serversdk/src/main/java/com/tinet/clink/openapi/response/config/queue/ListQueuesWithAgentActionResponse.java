package com.tinet.clink.openapi.response.config.queue;

import com.tinet.clink.openapi.model.QueueWithAgentActionModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListQueuesWithAgentActionResponse extends ResponseModel {

	private List<QueueWithAgentActionModel> queues;

	public List<QueueWithAgentActionModel> getQueues() {
		return queues;
	}

	public void setQueues(List<QueueWithAgentActionModel> queues) {
		this.queues = queues;
	}

}
