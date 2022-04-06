package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.ticket.GetTicketFileUrlRequest;
import com.tinet.clink.openapi.response.ticket.GetTicketFileUrlResponse;
import org.junit.Test;

/**
 * @description 获取工单中文件的下载url test
 * @Author shad0w
 * @Date 2022/04/06
 */
public class GetTicketFileUrlTest extends AbstractTest {

    @Test
    public void getTicketFileUrl() {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");

        Client client = new Client(configuration);

        // 创建请求的request
        GetTicketFileUrlRequest ticketFlowRequest = new GetTicketFileUrlRequest();
        // 请求参数
        ticketFlowRequest.setFileKey("fileKey");

        try {
            //将请求参数赋值到 request中
            GetTicketFileUrlResponse getTicketFileUrlResponse = client.getResponseModel(ticketFlowRequest);
            System.out.println(getTicketFileUrlResponse.getRequestId());
            System.out.println(getTicketFileUrlResponse.getFileUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
