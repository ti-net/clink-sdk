package com.tinet.clink.cc.response.exten;


import com.tinet.clink.cc.model.ListBindedExtensResultModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询话机列表响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class ListBindedExtensResponse extends PagedResponse {

    /**
     * 话机对象集合
     */
    List<ListBindedExtensResultModel> extens;

    public List<ListBindedExtensResultModel> getExtens() {
        return extens;
    }

    public void setExtens(List<ListBindedExtensResultModel> extens) {
        this.extens = extens;
    }
}