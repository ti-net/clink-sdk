package com.tinet.clink.cc.response.cloudnumber;


import com.tinet.clink.cc.model.CloudNumberCdrDetailModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * 云手机通话记录详情
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 **/
public class DescribeCloudNumberCdrResponse extends ResponseModel {

    /**
     * 主通话记录
     */
    private CloudNumberCdrDetailModel cloudNumberCdr;

    public CloudNumberCdrDetailModel getCloudNumberCdr() {
        return cloudNumberCdr;
    }

    public void setCloudNumberCdr(CloudNumberCdrDetailModel cloudNumberCdr) {
        this.cloudNumberCdr = cloudNumberCdr;
    }
}
