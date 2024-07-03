package com.tinet.clink.openapi.ticket;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.model.TicketCommentModel;
import com.tinet.clink.ticket.request.TicketCommentRequest;
import com.tinet.clink.ticket.response.TicketCommentResponse;
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
                "babc37aa470a92102016186b1cb79a82",          // AccessKeyId
                "vtq672M5Y48aI3728m14");     // AccessKeySecret
        configuration.setHost("127.0.0.1");
        configuration.setPort(8111);
        configuration.setScheme("http");

        Client client = new Client(configuration);

        // 创建请求的request
        TicketCommentRequest ticketCommentRequest = new TicketCommentRequest();
        // 请求参数
        TicketCommentModel ticketCommentModel = new TicketCommentModel();
        ticketCommentModel.setId(1773804);
        ticketCommentModel.setContent("content22222");
        ticketCommentModel.setCommentatorId("linyang3");
        ticketCommentModel.setCommentatorIdType(4);
        ticketCommentModel.setStateId("827");
        ticketCommentModel.setStateIdType(1);

        // 附件信息
        List<File> fileList = new ArrayList<>();
        File file = new File("D:\\钉钉三方应用二维码.png");
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
