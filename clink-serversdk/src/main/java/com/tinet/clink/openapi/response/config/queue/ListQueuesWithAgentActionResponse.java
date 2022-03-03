package com.tinet.clink.openapi.response.config.queue;

import com.tinet.clink.openapi.model.QueueWithAgentActionModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

public class ListQueuesWithAgentActionResponse extends ResponseModel {

	private List<QueueWithAgentActionModel> queues;

	public List<QueueWithAgentActionModel> getQueues() {
		return queues;
	}

	public void setQueues(List<QueueWithAgentActionModel> queues) {
		this.queues = queues;
	}

}
