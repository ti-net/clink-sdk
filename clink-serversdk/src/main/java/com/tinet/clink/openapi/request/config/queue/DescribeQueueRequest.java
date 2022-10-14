package com.tinet.clink.openapi.request.config.queue;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.queue.DescribeQueueResponse;
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
public class  DescribeQueueRequest extends AbstractRequestModel<DescribeQueueResponse> {

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

    public DescribeQueueRequest() {
        super(PathEnum.DescribeQueue.value(), HttpMethodType.GET);
    }

    @Override
    public Class<DescribeQueueResponse> getResponseClass() {
        return DescribeQueueResponse.class;
    }
}
