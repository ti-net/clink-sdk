package com.tinet.clink.cc.request.stat;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.stat.StatIvrNodesResponse;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * @author liurf
 * @date 2021/7/20 15:55
 **/
public class StatIvrNodesRequest extends AbstractStatRequest<StatIvrNodesResponse> {

    @Override
    public Class<StatIvrNodesResponse> getResponseClass() {
        return StatIvrNodesResponse.class;
    }

    public StatIvrNodesRequest() {
        super(PathEnum.StatIvrNodes.value(), HttpMethodType.POST);
    }

}
