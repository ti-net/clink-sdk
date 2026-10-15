package com.tinet.clink.ticket.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.ticket.model.TicketDetailModel;

import java.util.Map;

/**
 * @author liuhy
 * @date: 2020/9/8
 **/
public class GetTimeSlotDateResponse extends ResponseModel {


    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}