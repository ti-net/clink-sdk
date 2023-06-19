package com.tinet.clink.cc.response.cdr;


import com.tinet.clink.cc.model.OrderCallbackModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 预约回呼记录
 *
 * @author yinzk
 * @date 2023/6/9
 **/
public class ListOrderCallbackResponse extends ResponseModel {
    /**
     * 未接来电记录
     */
    private List<OrderCallbackModel> orderCallbacks;

    public List<OrderCallbackModel> getOrderCallbacks() {
        return orderCallbacks;
    }

    public void setOrderCallbacks(List<OrderCallbackModel> orderCallbacks) {
        this.orderCallbacks = orderCallbacks;
    }
}
