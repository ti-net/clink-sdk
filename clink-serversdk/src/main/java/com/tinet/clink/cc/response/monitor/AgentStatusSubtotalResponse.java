package com.tinet.clink.cc.response.monitor;

import com.tinet.clink.cc.model.MetaDataModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * Class for:
 * 座席状态小记响应对象
 *
 * @author : Tinet-yinzk
 * @date: 2023/6/4 21:44
 */
public class AgentStatusSubtotalResponse extends ResponseModel {
    /**
     * 座席当日状态小记
     */
    private List<MetaDataModel> statusSubtotal;

    public List<MetaDataModel> getStatusSubtotal() {
        return statusSubtotal;
    }

    public void setStatusSubtotal(List<MetaDataModel> statusSubtotal) {
        this.statusSubtotal = statusSubtotal;
    }
}
