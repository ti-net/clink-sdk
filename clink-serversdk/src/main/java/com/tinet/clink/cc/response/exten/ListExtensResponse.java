package com.tinet.clink.cc.response.exten;



import com.tinet.clink.cc.model.ListExtensResultModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询话机列表响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class ListExtensResponse extends PagedResponse {

    /**
     * 话机对象集合
     */
    List<ListExtensResultModel> extens;

    public List<ListExtensResultModel> getExtens() {
        return extens;
    }

    public void setExtens(List<ListExtensResultModel> extens) {
        this.extens = extens;
    }
}