package com.tinet.clink.openapi.response.ivr;

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
    private List<IvrNodeModel> listIvrNodes;

    public List<IvrNodeModel> getListIvrNodes() {
        return listIvrNodes;
    }

    public void setListIvrNodes(List<IvrNodeModel> listIvrNodes) {
        this.listIvrNodes = listIvrNodes;
    }
}
