package com.tinet.smartlink.openapi;

import com.tinet.smartlink.openapi.exceptions.ClientException;
import com.tinet.smartlink.openapi.exceptions.ServerException;
import com.tinet.smartlink.openapi.model.sqc.ChatRecord;
import com.tinet.smartlink.openapi.request.sqc.PushDialogueRequest;
import com.tinet.smartlink.openapi.response.sqc.PushDialogueResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Smartlink SDK测试类
 * 推送会话
 *
 * @author Ailos
 * @version 1.0
 * @date 2022/8/8 16:51
 */
public class PushDialogueTest {
    @Test
    public void test() {
        SmartlinkClientConfiguration configuration = new SmartlinkClientConfiguration(
                "<your-access-key-id>",          // AccessKeyId
                "<your-access-key-secret>");     // AccessKeySecret

        /*
            设置平台请求地址：
                测试环境：smartlink-sqc-openapi-test.tinetcloud.com
                生产环境：smartlink-sqc-openapi.tinetcloud.com
        */
        configuration.setHost("smartlink-sqc-openapi-test.tinetcloud.com");

        SmartlinkClient smartlinkClient = new SmartlinkClient(configuration);

        // 创建API请求并设置参数
        PushDialogueRequest request = new PushDialogueRequest();
        request.setUniqueId("f93902e8e14f4431997dbf72fd39fbf2-1660032894.1239");
        request.setAccountLoginName("demo");
        request.setUserId("1234567");
        request.setCustomerProvince("江苏省");
        request.setCustomerCity("南京市");
        request.setCustomerName("张三");
        request.setCustomerIp("127.0.0.1");
        request.setBrowserUserAgent("PC");
        request.setCno("C001");
        request.setAgentName("座席001");
        request.setQno("Q001");
        request.setQname("队列001");
        request.setStartTime(1660032894000L);
        request.setEndTime(1660042894000L);
        request.setSendChatTime(1660032894000L);
        request.setChatEndTime(1660042894000L);
        request.setEndReason("5");
        request.setInvestigation("满意");
        request.setCustomerNumber("12345678");
        request.setConversationFeature("people");
        // 座席侧会话
        List<ChatRecord> agentChatRecord = new ArrayList<>();
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setId("1");
        chatRecord.setRole(0);
        chatRecord.setSendTime(1660032894000L);
        chatRecord.setMessageType(1);
        chatRecord.setText("Hello!");
        agentChatRecord.add(chatRecord);
        request.setChatAgent(agentChatRecord);
        // 客户侧会话
        List<ChatRecord> customerChatRecord = new ArrayList<>();
        chatRecord = new ChatRecord();
        chatRecord.setId("2");
        chatRecord.setRole(1);
        chatRecord.setSendTime(1660032894000L);
        chatRecord.setMessageType(1);
        chatRecord.setText("你好!");
        customerChatRecord.add(chatRecord);
        request.setChatCustomer(customerChatRecord);
        request.setChatRecordStatus(3);
        request.setChatType(1);

        PushDialogueResponse response;
        try {
            // 发起API调用并获得返回结果
            response = smartlinkClient.getResponseModel(request);
            // 打印返回结果
            System.out.println(response);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
