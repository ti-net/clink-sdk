package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.ticket.ListTicketWorkflowRequest;
import com.tinet.clink.openapi.response.ticket.ListTicketWorkflowResponse;
import org.junit.Test;

/**工单模板查询请求示例
 * @date 2020/11/17
 **/
public class ListTicketWorkflowTest extends AbstractTest {

    @Test
    public void listTicketWorkflow() {

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);

        // 创建请求的request
        ListTicketWorkflowRequest listTicketWorkflowRequest = new ListTicketWorkflowRequest();

        // 赋值参数
        listTicketWorkflowRequest.setOffset(0);
        listTicketWorkflowRequest.setLimit(20);
        listTicketWorkflowRequest.setCategory(112);

        ListTicketWorkflowResponse ticketWorkflowResponse;

        try {
            ticketWorkflowResponse = client.getResponseModel(listTicketWorkflowRequest);
            System.out.println(ticketWorkflowResponse.getWorkflows().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
