package com.tinet.clink.cc.request.bot;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.bot.BotTransferResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 外呼请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class BotTransferRequest extends AbstractRequestModel<BotTransferResponse> {

    private String mainUniqueId;

    private String transferType;

    private String transferObject;

    public BotTransferRequest() {
        super(PathEnum.BotTransfer.value(), HttpMethodType.POST);
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putBodyParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
        if (transferType != null) {
            putBodyParameter("transferType", transferType);
        }
    }

    public String getTransferObject() {
        return transferObject;
    }

    public void setTransferObject(String transferObject) {
        this.transferObject = transferObject;
        if (transferObject != null) {
            putBodyParameter("transferObject", transferObject);
        }
    }

    @Override
    public Class<BotTransferResponse> getResponseClass() {
        return BotTransferResponse.class;
    }
}
