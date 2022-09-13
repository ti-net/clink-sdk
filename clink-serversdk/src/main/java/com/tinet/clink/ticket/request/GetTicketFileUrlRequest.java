package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.GetTicketFileUrlResponse;
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