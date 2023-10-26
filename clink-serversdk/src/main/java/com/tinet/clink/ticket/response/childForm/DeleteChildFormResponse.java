package com.tinet.clink.ticket.response.childForm;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.ticket.model.childForm.ListChildFormResultModel;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/8/20
 **/
public class DeleteChildFormResponse extends ResponseModel {

    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}