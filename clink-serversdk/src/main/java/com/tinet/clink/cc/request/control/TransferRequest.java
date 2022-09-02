package com.tinet.clink.cc.request.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.TransferResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 转移请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class TransferRequest extends AbstractRequestModel<TransferResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 转移类型，0:电话号码；1:座席号；2:分机号；3:语音导航节点；4:语音导航名称
     */
    private Integer transferType;

    /**
     * 转移对象号码
     */
    private String transferNumber;

    public TransferRequest() {
        super(PathEnum.Transfer.value(), HttpMethodType.POST);
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
        if (transferType != null) {
            putBodyParameter("transferType", transferType);
        }
    }

    public String getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(String transferNumber) {
        this.transferNumber = transferNumber;
        if (transferNumber != null) {
            putBodyParameter("transferNumber", transferNumber);
        }
    }

    @Override
    public Class<TransferResponse> getResponseClass() {
        return TransferResponse.class;
    }
}
