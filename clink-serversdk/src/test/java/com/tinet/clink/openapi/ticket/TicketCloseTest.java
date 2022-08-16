package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.model.TicketCommentModel;
import com.tinet.clink.openapi.request.ticket.TicketCloseRequest;
import com.tinet.clink.openapi.request.ticket.TicketCommentRequest;
import com.tinet.clink.openapi.response.ticket.TicketCloseResponse;
import com.tinet.clink.openapi.response.ticket.TicketCommentResponse;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketCloseTest extends AbstractTest {

    @Test
    public void ticketComment(){
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");

        Client client = new Client(configuration);

        // 创建请求的request
        TicketCloseRequest ticketCloseRequest = new TicketCloseRequest();
        // 请求参数
        // 关闭操作备注信息
        ticketCloseRequest.setContent("工单关闭");
        //关闭人（当前操作人的id）
        ticketCloseRequest.setOperator("1564");

        try {
            // 将请求参数赋值到 request中
            TicketCloseResponse ticketCloseResponse = client.getResponseModel(ticketCloseRequest);
            System.out.println(ticketCloseResponse.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
