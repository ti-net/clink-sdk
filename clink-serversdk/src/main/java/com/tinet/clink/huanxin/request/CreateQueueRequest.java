package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.PathEnum;
import com.tinet.clink.huanxin.response.CreateQueueResponse;

/**
 * 创建队列
 *
 * @author tian.jie
 * @date 2024-01-18 11:33
 */
public class CreateQueueRequest extends AbstractRequestModel<CreateQueueResponse> {

    /**
     * 云呼：CallCenter，在线：Message
     */
    private String queueType;
    /**
     * 技能组名称 （不能超过20）
     */
    private String name;

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateQueueRequest() {
        super(PathEnum.CREATE_QUEUE.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateQueueResponse> getResponseClass() {
        return CreateQueueResponse.class;
    }
}
