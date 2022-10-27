package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.request.ChatRecordInfoRequest;
import com.tinet.clink.livechat.response.ChatRecordResponse;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhaozq
 * @date 2022.03.24
 */
public class ChatRecordInfoTest extends AbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("53679ca2485f1f2efdae5d5df3a38ad2", "jZ0M9453ZLZr7n24rQ56");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test4.clink.cn");

        client = new Client(configuration);
    }

    @Test
    public void testOpenSession() throws Exception {
        ChatRecordInfoRequest request = new ChatRecordInfoRequest();
//        request.setMainUniqueId("39902709-52f3-4976-9b5d-2c843daee1de.1648090535");
        request.setMainUniqueId("20935007-dac8-4cb2-b67f-d5677baeebc0.1648108890");
        request.setDate("20220324");

        ChatRecordResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }
}
