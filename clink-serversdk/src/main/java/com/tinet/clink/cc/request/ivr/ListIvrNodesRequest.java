package com.tinet.clink.cc.request.ivr;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.ivr.ListIvrNodesResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 查询ivr节点列表请求
 *
 * @author huwk
 * @date 2018/11/15
 **/
public class ListIvrNodesRequest extends AbstractRequestModel<ListIvrNodesResponse> {

    /**
     * 语音导航名称
     */
    private String ivrName;

    public ListIvrNodesRequest() {
        super(PathEnum.ListIvrNodes.value(), HttpMethodType.GET);
    }

    public String getIvrName() {
        return ivrName;
    }

    public void setIvrName(String ivrName) {
        this.ivrName = ivrName;
        if (ivrName != null) {
            putQueryParameter("ivrName", ivrName);
        }
    }

    @Override
    public Class<ListIvrNodesResponse> getResponseClass() {
        return ListIvrNodesResponse.class;
    }
}
