package com.tinet.clink.ticket.response.childForm;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.ticket.model.childForm.CreateChildFormResultModel;
import com.tinet.clink.ticket.model.childForm.ListChildFormResultModel;

import java.util.List;

/**
 *
 * @author liuhy
 * @date: 2020/8/20
 **/
public class CreateChildFormResponse extends ResponseModel {
    CreateChildFormResultModel data;

    public CreateChildFormResultModel getData() {
        return data;
    }

    public void setData(CreateChildFormResultModel data) {
        this.data = data;
    }
}