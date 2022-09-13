package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.model.ChatInvestigationOptionModel;
import com.tinet.clink.livechat.request.ChatSubmitInvestigationRequest;
import com.tinet.clink.livechat.response.ChatSubmitInvestigationResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 会话开始
 */
public class ChatSubmitInvestigationTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("706ff5f9bbb10286dcf7545262a7d702", "IO9Fpa392A3y54375Tvu");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test3.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testSubmitInvestigation() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatSubmitInvestigationRequest request = new ChatSubmitInvestigationRequest();
        request.setSessionId("8295d45a-6df4-4dc8-aacc-05c5d2ee547d.1622013840");

        ChatInvestigationOptionModel[] options = new ChatInvestigationOptionModel[1];
        options[0] = new ChatInvestigationOptionModel();
        options[0].setName("评价名称");
        String[] label = new String[2];
        label[0] = "标签1";
        label[1] = "标签2";
        options[0].setLabel(label);
        options[0].setStar(5);
        request.setOptions(options);

        request.setRemark("评价备注");

        request.setSolve(1);
        ChatSubmitInvestigationResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
