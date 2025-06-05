package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.TicketMockSaveRequest;
import com.tinet.clink.ticket.response.TicketMockCommonResponse;
import org.junit.Test;

/**
 * 工单催单请求示例
 *
 * @author fzq
 * @date 2023-10-13 13:35:35
 */
public class TicketMockUpdateTest extends AbstractTest {

    @Test
    public void ticketMockReminder() {

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "657c8b11f7ae9bc9b51ec67747fc24f5",          // AccessKeyId
                "5924A00GL81m97NB0zH9");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求request
        TicketMockSaveRequest ticketMockSaveRequest =  new TicketMockSaveRequest();
        ticketMockSaveRequest.setWorkflowName("wjn串行预置");

        TicketMockCommonResponse ticketMockResponse ;

        try {
            ticketMockResponse = client.getResponseModel(ticketMockSaveRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(ticketMockResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
