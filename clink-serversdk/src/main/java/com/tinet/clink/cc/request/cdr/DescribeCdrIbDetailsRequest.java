package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.DescribeCdrIbDetailsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 查询呼入通话记录明细详情请求
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class DescribeCdrIbDetailsRequest extends AbstractRequestModel<DescribeCdrIbDetailsResponse> {

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    public DescribeCdrIbDetailsRequest() {
        super(PathEnum.DescribeCdrIbDetails.value(), HttpMethodType.GET);
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
    public Class<DescribeCdrIbDetailsResponse> getResponseClass() {
        return DescribeCdrIbDetailsResponse.class;
    }
}
