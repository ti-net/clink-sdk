package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.TicketMockReminderRequest;
import com.tinet.clink.ticket.request.TicketMockSaveRequest;
import com.tinet.clink.ticket.response.TicketMockCommonResponse;
import org.junit.Test;

/**
 * 工单催单请求示例
 *
 * @author fzq
 * @date 2023-10-13 13:35:35
 */
public class TicketMockSaveTest extends AbstractTest {

    @Test
    public void ticketMockSave() {

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "9abf58ac259bf7ca8f69788b04c45bb7",          // AccessKeyId
                "s1L55xP7JF1J9I90a684");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求request
        TicketMockSaveRequest ticketMockSaveRequest =  new TicketMockSaveRequest();
        ticketMockSaveRequest.setWorkflowName("知识审核_UI测试");

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
