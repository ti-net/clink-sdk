package com.tinet.clink.cc.request.client;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.client.DescribeClientResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 *
 * @author lizy
 * @date 2018/10/24
 */
public class DescribeClientRequest extends AbstractRequestModel<DescribeClientResponse> {
    /**
     * 座席号
     */
    private String cno;

    public DescribeClientRequest() {
        super(PathEnum.DescribeClient.value(), HttpMethodType.GET);
    }


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putQueryParameter("cno", cno);
        }
    }

    @Override
    public Class<DescribeClientResponse> getResponseClass() {
        return DescribeClientResponse.class;
    }
}
