package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.TicketRestartRequest;
import com.tinet.clink.ticket.response.TicketRestartResponse;
import org.junit.Test;

/**
 * 工单重启请求示例
 *
 * @author fzq
 * @date 2023-10-13 14:57:16
 */
public class TicketRestartTest extends AbstractTest {

    @Test
    public void ticketRestart() {

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求request
        TicketRestartRequest ticketRestartRequest = new TicketRestartRequest();
        ticketRestartRequest.setContent("重启测试");
        ticketRestartRequest.setTicketId(1769505);
        ticketRestartRequest.setAfterRestartHandlerId(20932);

        TicketRestartResponse ticketRestartResponse;

        try {
            ticketRestartResponse = client.getResponseModel(ticketRestartRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(ticketRestartResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
