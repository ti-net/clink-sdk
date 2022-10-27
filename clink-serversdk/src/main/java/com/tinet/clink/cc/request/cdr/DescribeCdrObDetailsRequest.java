package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.DescribeCdrObDetailsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 查询外呼通话记录明细详情请求
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class DescribeCdrObDetailsRequest extends AbstractRequestModel<DescribeCdrObDetailsResponse> {

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    public DescribeCdrObDetailsRequest() {
        super(PathEnum.DescribeCdrObDetails.value(), HttpMethodType.GET);
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
    public Class<DescribeCdrObDetailsResponse> getResponseClass() {
        return DescribeCdrObDetailsResponse.class;
    }
}
