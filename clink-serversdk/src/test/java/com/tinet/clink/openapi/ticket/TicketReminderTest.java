package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.model.TicketReminderModel;
import com.tinet.clink.ticket.request.TicketReminderRequest;
import com.tinet.clink.ticket.response.TicketReminderResponse;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单催单请求示例
 *
 * @author fzq
 * @date 2023-10-13 13:35:35
 */
public class TicketReminderTest extends AbstractTest {

    @Test
    public void ticketReminder() {

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求request
        TicketReminderRequest ticketReminderRequest = new TicketReminderRequest();

        TicketReminderModel param = new TicketReminderModel();
        param.setTicketId(1769202);
        param.setUserId(20932);
        param.setReminderRemark("费中秋测试");
        ticketReminderRequest.setModel(param);

        List<File> fileList = new ArrayList<>();
        File file = new File("C:\\Users\\admin\\Desktop\\组织结构.xlsx");
        fileList.add(file);
        Map<String, List<File>> fileMap = new HashMap<>();
        fileMap.put("files", fileList);
        ticketReminderRequest.setFileMap(fileMap);

        TicketReminderResponse ticketReminderResponse;

        try {
            ticketReminderResponse = client.getResponseModel(ticketReminderRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(ticketReminderResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
