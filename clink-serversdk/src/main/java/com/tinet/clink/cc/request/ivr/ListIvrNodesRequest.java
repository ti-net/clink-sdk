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
     * 语音导航id
     */
    private Integer ivrId;

    public ListIvrNodesRequest() {
        super(PathEnum.ListIvrNodes.value(), HttpMethodType.GET);
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
        if (ivrId != null) {
            putQueryParameter("ivrId", ivrId);
        }
    }

    @Override
    public Class<ListIvrNodesResponse> getResponseClass() {
        return ListIvrNodesResponse.class;
    }
}
