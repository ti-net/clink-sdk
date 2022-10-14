package com.tinet.clink.cc.request.queue;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.queue.DeleteQueueResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 删除队列请求
 *
 * @author lizy
 * @date 2018/10/25
 */
public class DeleteQueueRequest extends AbstractRequestModel<DeleteQueueResponse> {

    /**
     * 队列号
     */
    private String qno;


    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
        if (qno != null) {
            putQueryParameter("qno", qno);
        }
    }

    public DeleteQueueRequest() {
        super(PathEnum.DeleteQueue.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteQueueResponse> getResponseClass() {
        return DeleteQueueResponse.class;
    }
}
