package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 获取文件url的响应内容
 *
 * @author shad0w
 * @date: 2022/4/6
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  GetTicketFileUrlResponse extends ResponseModel {

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