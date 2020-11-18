package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketUploadResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 通用上传文件请求
 * 1、重写isMultipartFormData方法，使其支持 multipart form data 请求方式
 * 2、如果有text参数，传入到父类的到 model中；如果有附件，传入到父类的 fileMap中。
 *
 * @author huwk
 * @date 2020/11/15
 **/
public class TicketUploadRequest extends AbstractRequestModel<TicketUploadResponse> {

    public TicketUploadRequest() {
        super("/upload", HttpMethodType.POST);
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
