package com.tinet.clink.openapi.ticket;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.TicketAssignRequest;
import com.tinet.clink.ticket.response.TicketAssignResponse;
import org.junit.Test;


public class TicketAssignTest extends AbstractTest {
    @Test
    public void ticketAssign() {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "3ee29817e02c59ab4f27f55b75053ab9",          // AccessKeyId
                "4498PzDejj8y13e2u19Q");     // AccessKeySecret
        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketAssignRequest ticketAssignRequest = new TicketAssignRequest();
        // 请求参数
        // 操作人的 ID
        ticketAssignRequest.setOperatorId(1028634);
        // 工单的 ID
        ticketAssignRequest.setId(379060);
        // 处理人的类型
        ticketAssignRequest.setHandlerType(0);
        // 处理人的 ID
        ticketAssignRequest.setHandlerId(1028634);
        // 是否开启自动分配
        ticketAssignRequest.setAutoAssign(0);
        // 任务 Id
        ticketAssignRequest.setTaskId("c2405b1c-fdec-11ed-8137-362f2c7b423c");
        // 分配工单备注的内容
        ticketAssignRequest.setContent("工单分配");

        try {
            // 将请求参数赋值到 request中
            TicketAssignResponse ticketAssignResponse = client.getResponseModel(ticketAssignRequest);
            System.out.println(ticketAssignResponse.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}