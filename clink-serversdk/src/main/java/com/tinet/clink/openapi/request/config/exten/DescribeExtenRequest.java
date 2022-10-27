package com.tinet.clink.openapi.request.config.exten;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.exten.DescribeExtenResponse;

/**
 * 查询话机详情请求
 *
 * @author wangyq
 * @date 2018/10/26
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeExtenRequest extends AbstractRequestModel<DescribeExtenResponse> {

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