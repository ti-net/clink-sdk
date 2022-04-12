package com.tinet.clink.openapi.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.DescribeQueueResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除队列请求
 *
 * @author lizy
 * @date 2018/10/25
 */
public class DescribeQueueRequest extends AbstractRequestModel<DescribeQueueResponse> {

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
