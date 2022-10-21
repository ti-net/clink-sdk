package com.tinet.clink.openapi.request.config.queue;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.queue.DeleteQueueResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除队列请求
 *
 * @author lizy
 * @date 2018/10/25
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteQueueRequest extends AbstractRequestModel<DeleteQueueResponse> {

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
