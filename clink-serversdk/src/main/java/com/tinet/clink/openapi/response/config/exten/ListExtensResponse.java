package com.tinet.clink.openapi.response.config.exten;

import com.tinet.clink.openapi.model.ListExtensResultModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询话机列表响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListExtensResponse extends PagedResponse {

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