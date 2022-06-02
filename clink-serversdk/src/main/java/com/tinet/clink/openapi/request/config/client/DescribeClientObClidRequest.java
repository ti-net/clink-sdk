package com.tinet.clink.openapi.request.config.client;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.client.DescribeClientObClidResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author wangli
 * @date 2022-05-30 7:13 PM
 */
public class DescribeClientObClidRequest extends AbstractRequestModel<DescribeClientObClidResponse> {

    /**
     * 座席号
     */
    private String cno;

    public DescribeClientObClidRequest() {
        super(PathEnum.DescribeClientObClid.value(), HttpMethodType.GET);
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
    public Class<DescribeClientObClidResponse> getResponseClass() {
        return DescribeClientObClidResponse.class;
    }

}
