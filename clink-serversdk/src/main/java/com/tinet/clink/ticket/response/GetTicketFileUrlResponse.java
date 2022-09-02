package com.tinet.clink.ticket.response;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 获取文件url的响应内容
 *
 * @author shad0w
 * @date: 2022/4/6
 **/
public class GetTicketFileUrlResponse extends ResponseModel {

    /**
     * 文件下载链接
     */
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}