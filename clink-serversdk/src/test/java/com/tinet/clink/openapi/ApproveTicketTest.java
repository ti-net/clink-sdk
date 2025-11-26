package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.ticket.request.TicketApprovalRequest;
import com.tinet.clink.ticket.response.TicketApprovalResponse;
import org.junit.Test;

/**
 * 工单审批测试
 *
 * @author auto-generated
 * @date 2024
 */
public class ApproveTicketTest {

    @Test
    public void approveTicket() {
        // 配置客户端
        ClientConfiguration configuration = new ClientConfiguration(
                "657c8b11f7ae9bc9b51ec67747fc24f5",           // AccessKeyId
                "5924A00GL81m97NB0zH9");                    // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        configuration.setPort(443);
        Client client = new Client(configuration);

        TicketApprovalRequest request = new TicketApprovalRequest();

        // 设置必传参数：任务ID
        request.setTaskId("6379ee89-c92a-11f0-91cd-72a0b78466ce");

        // 设置工单ID（二选一：id 或 externalId）
        request.setId(74187);
        // 或者使用外部ID
        // request.setExternalId("123459877");

        // 设置必传参数：审批人ID
        request.setApproverId("wxhui");
        request.setApproverIdType(3);

        // 设置必传参数：审批结果（0-同意，1-拒绝）
        request.setApprovalResult(0);

        // 设置可选参数：审批意见
//        request.setApprovalComment("审批通过，同意该工单的处理方案");

        try {
            // 发送请求
            TicketApprovalResponse response = client.getResponseModel(request);

            // 输出响应结果
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("审批结果：");
            System.out.println(objectMapper.writeValueAsString(response));

        } catch (Exception e) {
            System.err.println("工单审批失败：");
            e.printStackTrace();
        }
    }
}

