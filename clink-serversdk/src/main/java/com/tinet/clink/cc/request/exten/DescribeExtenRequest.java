package com.tinet.clink.cc.request.exten;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.exten.DescribeExtenResponse;
import com.tinet.clink.core.request.AbstractRequestModel;


/**
 * 查询话机详情请求
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class DescribeExtenRequest extends AbstractRequestModel<DescribeExtenResponse> {

    /**
     * 话机号码
     */
    private String extenNumber;

    public DescribeExtenRequest() {
        super(PathEnum.DescribeExten.value());
    }

    public String getExtenNumber() {
        return extenNumber;
    }

    public void setExtenNumber(String extenNumber) {
        this.extenNumber = extenNumber;
        if (extenNumber != null) {
            putQueryParameter("extenNumber", extenNumber);
        }
    }

    @Override
    public Class<DescribeExtenResponse> getResponseClass() {
        return DescribeExtenResponse.class;
    }
}