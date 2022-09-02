package com.tinet.clink.cc.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.SqcAsrResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 质检转写文本获取
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class SqcAsrRequest extends AbstractRequestModel<SqcAsrResponse> {



    /**
     * 唯一标识
     */
    private String mainUniqueId;

    public SqcAsrRequest() {
        super(PathEnum.sqcAsr.value(),HttpMethodType.GET);
    }


    public String getMainUniqueId() {
        return mainUniqueId;
    }


    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    @Override
    public Class<SqcAsrResponse> getResponseClass() {
        return SqcAsrResponse.class;
    }
}
