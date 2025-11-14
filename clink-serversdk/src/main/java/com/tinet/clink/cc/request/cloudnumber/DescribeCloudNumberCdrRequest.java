package com.tinet.clink.cc.request.cloudnumber;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cloudnumber.DescribeCloudNumberCdrResponse;
import com.tinet.clink.core.request.AbstractRequestModel;

/**
 * 查询云手机通话记录详情请求
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 **/
public class DescribeCloudNumberCdrRequest extends AbstractRequestModel<DescribeCloudNumberCdrResponse> {

    /**
     * 通话记录唯一标识
     */
    private String uniqueId;

    /**
     * 是否隐藏号码
     */
    private Integer hiddenType;


    public DescribeCloudNumberCdrRequest() {
        super(PathEnum.DescribeCloudNumberCdr.value(), com.tinet.clink.core.utils.HttpMethodType.GET);
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putQueryParameter("uniqueId", uniqueId);
        }
    }

    public Integer getHiddenType() {
        return hiddenType;
    }

    public void setHiddenType(Integer hiddenType) {
        this.hiddenType = hiddenType;
        if (hiddenType != null) {
            putQueryParameter("hiddenType", hiddenType);
        }
    }


    @Override
    public Class<DescribeCloudNumberCdrResponse> getResponseClass() {
        return DescribeCloudNumberCdrResponse.class;
    }
}
