package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatVisitorInfoRequest;
import com.tinet.clink.openapi.response.chat.ChatVisitorInfoResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * @author midong
 * @date 2022.06.08
 */
public class ChatVisitorInfoTest extends AbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("6579a76bec561954ae1ad346c312d862", "K37z0S685347hryiudXa");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test0.clink.cn");
        client = new Client(configuration);
        System.out.println("client--->>>>{}");
    }

    @Test
    public void testOpenSession() throws Exception {
        ChatVisitorInfoRequest request = new ChatVisitorInfoRequest();
        request.setVisitorId("8609c4cf-fcf9-4efa-bc3d-02dc888a8443");
        request.setAccessId("ac89d543-30ff-4d15-b5c4-6e1a9cc80c25");
        ChatVisitorInfoResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
        System.out.println(response.getUnreadCount());
    }
}
