package com.tinet.clink.openapi.request.monitor;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.monitor.AgentStatusResponse;
import com.tinet.clink.openapi.response.monitor.QueueStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询队列状态请求对象
 *
 * @author wangll
 * @date 2019/11/20
 **/
public class QueueStatusRequest extends AbstractRequestModel<QueueStatusResponse> {

    /**
     * 队列号数组
     */
    private String[] qnos;

    public QueueStatusRequest() {
        super(PathEnum.QueueStatus.value(), HttpMethodType.GET);
    }

    @Override
    public Class<QueueStatusResponse> getResponseClass() {
        return QueueStatusResponse.class;
    }

    public String[] getQnos() {
        return qnos;
    }

    public void setQnos(String[] qnos) {
        this.qnos = qnos;
        if (qnos != null) {
            for (int i = 0; i < qnos.length; i++) {
                putQueryParameter("qnos[" + i + "]", qnos[i]);
            }
        }
    }
}
