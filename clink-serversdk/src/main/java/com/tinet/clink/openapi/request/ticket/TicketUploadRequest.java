package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketUploadResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author huwk
 * @date 2020/11/15
 **/
public class TicketUploadRequest extends AbstractRequestModel<TicketUploadResponse> {

    public TicketUploadRequest() {
        super("/upload", HttpMethodType.POST);
    }

    public void setModel(String model) {

        this.model = model;
    }

    public void setFileMap(Map<String,List<File>> fileMap) {
        this.fileMap = fileMap;
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }


    @Override
    public Class<TicketUploadResponse> getResponseClass() {
        return TicketUploadResponse.class;
    }
}
