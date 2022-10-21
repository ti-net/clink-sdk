package com.tinet.clink.cc.response.queue;

import com.tinet.clink.cc.model.QueueWithAgentActionModel;
import com.tinet.clink.core.response.ResponseModel;

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
