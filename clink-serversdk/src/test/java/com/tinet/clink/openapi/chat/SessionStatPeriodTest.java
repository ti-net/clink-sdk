package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.livechat.request.SessionStatPeriodRequest;
import com.tinet.clink.livechat.response.SessionStatPeriodResponse;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * 在线客服 - 会话量报表_按会话量统计
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionStatPeriodTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void sessionStatPeriodTest() throws ServerException, ClientException, JsonProcessingException {
        SessionStatPeriodRequest request = new SessionStatPeriodRequest();
        request.setStartTime("2024-12-10 00:25");
        request.setEndTime("2024-12-10 10:25");
        List<Integer> contactTypes = Collections.singletonList(1);
        request.setContactTypes(contactTypes);
        request.setPeriodType(1);
        ClientConfiguration clientConfiguration = new ClientConfiguration("58675b79b5582fe19ccb891ee1f8418a", "3l3q23wmX1WV4528x910");
        clientConfiguration.setScheme("https");
        clientConfiguration.setHost("api-bj-test0.clink.cn");
        Client client = new Client(clientConfiguration);
        SessionStatPeriodResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getData()));
    }
}
