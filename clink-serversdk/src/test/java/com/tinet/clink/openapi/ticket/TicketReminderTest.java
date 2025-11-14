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
                "657c8b11f7ae9bc9b51ec67747fc24f5",          // AccessKeyId
                "5924A00GL81m97NB0zH9");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求request
        TicketReminderRequest ticketReminderRequest = new TicketReminderRequest();

        TicketReminderModel param = new TicketReminderModel();
        param.setTicketId(69813);
        param.setUserId(-3);
        param.setTaskId("91defae5-411a-11f0-ab65-a2d2351e2cae");
        param.setReminderRemark("催单测试");
        ticketReminderRequest.setModel(param);

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
