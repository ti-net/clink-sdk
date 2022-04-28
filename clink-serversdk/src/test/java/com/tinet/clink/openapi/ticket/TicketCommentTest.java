package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.model.TicketCommentModel;
import com.tinet.clink.openapi.request.ticket.TicketCommentRequest;
import com.tinet.clink.openapi.response.ticket.TicketCommentResponse;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TicketCommentTest extends AbstractTest {

    @Test
    public void ticketComment(){
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");

        Client client = new Client(configuration);

        // 创建请求的request
        TicketCommentRequest ticketCommentRequest = new TicketCommentRequest();
        // 请求参数
        TicketCommentModel ticketCommentModel = new TicketCommentModel();
        ticketCommentModel.setId(1);
        ticketCommentModel.setContent("content22222");

        // 附件信息
        List<File> fileList = new ArrayList<>();
        File file = new File("51915.xlsx");
        fileList.add(file);
        Map<String, List<File>> fileMap = new HashMap<>();
        fileMap.put("fileName", fileList);
        ticketCommentRequest.setFileMap(fileMap);

        ticketCommentRequest.setModel(ticketCommentModel);
        try {
            // 将请求参数赋值到 request中
            TicketCommentResponse ticketCommentResponse = client.getResponseModel(ticketCommentRequest);
            System.out.println(ticketCommentResponse.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
