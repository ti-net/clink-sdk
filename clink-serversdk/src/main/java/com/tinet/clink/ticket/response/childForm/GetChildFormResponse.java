package com.tinet.clink.ticket.response.childForm;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.ticket.model.childForm.ListChildFormResultModel;
import com.tinet.clink.ticket.model.childForm.OpenapiFormModel;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/8/20
 **/
public class GetChildFormResponse extends ResponseModel {


    OpenapiFormModel data;

    public OpenapiFormModel getData() {
        return data;
    }

    public void setData(OpenapiFormModel data) {
        this.data = data;
    }
}