package com.tinet.clink.ticket.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 *
 * @author liuhy
 * @date: 2020/11/22
 **/
public class TicketMockCommonResponse extends ResponseModel {

    /**
     * 工单id
     */
    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}