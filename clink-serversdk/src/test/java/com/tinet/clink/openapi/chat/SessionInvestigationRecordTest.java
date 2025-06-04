package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.livechat.request.SessionInvestigationRecordRequest;
import com.tinet.clink.livechat.response.SessionInvestigationRecordResponse;
import org.junit.Test;

/**
 * 会话详情查看
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionInvestigationRecordTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void sessionInvestigationRecordTest() throws ServerException, ClientException, JsonProcessingException {
        SessionInvestigationRecordRequest request = new SessionInvestigationRecordRequest();
        request.setStartTime("2024-12-10 00:25");
        request.setEndTime("2024-12-10 10:25");
        request.setPageIndex(1);
        request.setPageSize(1);
        ClientConfiguration clientConfiguration = new ClientConfiguration("58675b79b5582fe19ccb891ee1f8418a", "3l3q23wmX1WV4528x910");
        clientConfiguration.setScheme("https");
        clientConfiguration.setHost("api-bj-test0.clink.cn");
        Client client = new Client(clientConfiguration);
        SessionInvestigationRecordResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getData()));
    }
}
