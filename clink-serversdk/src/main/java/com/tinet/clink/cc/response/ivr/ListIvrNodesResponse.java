package com.tinet.clink.cc.response.ivr;

import com.tinet.clink.openapi.model.IvrNodeModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 查询ivr节点列表响应
 *
 * @author huwk
 * @date 2018/11/15
 **/
public class ListIvrNodesResponse extends ResponseModel {

    /**
     * ivr节点对象列表
     */
    private List<IvrNodeModel> ivrNodes;


    public List<IvrNodeModel> getIvrNodes() {
        return ivrNodes;
    }

    public void setIvrNodes(List<IvrNodeModel> ivrNodes) {
        this.ivrNodes = ivrNodes;
    }
}
