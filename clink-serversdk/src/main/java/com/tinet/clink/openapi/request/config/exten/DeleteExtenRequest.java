package com.tinet.clink.openapi.request.config.exten;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.exten.DeleteExtenResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除话机请求
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class DeleteExtenRequest extends AbstractRequestModel<DeleteExtenResponse> {

    /**
     * 话机号码
     */
    private String extenNumber;

    public DeleteExtenRequest() {
        super(PathEnum.DeleteExten.value(), HttpMethodType.POST);
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
    public Class<DeleteExtenResponse> getResponseClass() {
        return DeleteExtenResponse.class;
    }
}