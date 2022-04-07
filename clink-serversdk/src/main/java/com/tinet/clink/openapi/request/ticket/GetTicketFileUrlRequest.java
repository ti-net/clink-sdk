package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.GetTicketFileUrlResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;
import org.apache.http.util.Asserts;

/**
 * 获取工单中文件的下载url
 *
 * @author shad0w
 * @date: 2022/4/6
 **/
public class GetTicketFileUrlRequest extends AbstractRequestModel<GetTicketFileUrlResponse> {

    private String fileKey;

    public GetTicketFileUrlRequest() {
        super(PathEnum.GetTicketFileUrl.value(), HttpMethodType.POST);
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
        Asserts.notBlank(fileKey, "param fileKey required,");
        putQueryParameter("fileKey", fileKey);
    }

    @Override
    public Class<GetTicketFileUrlResponse> getResponseClass() {
        return GetTicketFileUrlResponse.class;
    }
}