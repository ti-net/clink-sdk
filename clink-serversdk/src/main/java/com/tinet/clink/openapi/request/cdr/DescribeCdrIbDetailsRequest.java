package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.DescribeCdrIbDetailsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询呼入通话记录明细详情请求
 *
 * @author huwk
 * @date 2018/10/24
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeCdrIbDetailsRequest extends AbstractRequestModel<DescribeCdrIbDetailsResponse> {

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
