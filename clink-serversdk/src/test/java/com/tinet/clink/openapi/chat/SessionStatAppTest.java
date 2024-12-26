package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.livechat.request.SessionStatAppRequest;
import com.tinet.clink.livechat.response.SessionStatAppResponse;
import java.util.Collections;
import org.junit.Test;

import java.util.List;

/**
 * 在线客服 - 会话量报表_按接入号统计
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionStatAppTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void sessionStatAppTest() throws ServerException, ClientException, JsonProcessingException {
        SessionStatAppRequest request= new SessionStatAppRequest();
        request.setStartTime("2024-12-10 00:25");
        request.setEndTime("2024-12-10 23:25");
        request.setPageIndex(1);
        request.setPageSize(2);
        List<Integer> appType = Collections.singletonList(1);
        request.setAppType(appType);
        ClientConfiguration clientConfiguration = new ClientConfiguration("58675b79b5582fe19ccb891ee1f8418a", "3l3q23wmX1WV4528x910");
        clientConfiguration.setScheme("https");
        clientConfiguration.setHost("api-bj-test0.clink.cn");
        Client client = new Client(clientConfiguration);
        SessionStatAppResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getData()));
    }
}
