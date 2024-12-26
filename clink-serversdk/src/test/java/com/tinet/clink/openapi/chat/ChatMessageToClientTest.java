package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.request.ChatMessageToClientRequest;
import com.tinet.clink.livechat.response.ChatMessageToClientResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ChatMessageToClientTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        configuration = new ClientConfiguration("58675b79b5582fe19ccb891ee1f8418a", "3l3q23wmX1WV4528x910");
        configuration.setScheme("https");
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setEnv("gray");
        client = new Client(configuration);
    }




    @Test
    public void testMessageToClient() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        ChatMessageToClientRequest request = new ChatMessageToClientRequest();
        request.setSessionId("a006b5f2-f181-4cd8-a678-46864b6b303b.1733970993");
        request.setMessageType(1);
        request.setContent("一样一样");

        // multipartFile 转file https://blog.csdn.net/qisoft1213/article/details/105098007
//        request.setFile(new File("F:\\钉钉缓存\\绩效管理制度配套附表-20210121(1).xlsx"));
        ChatMessageToClientResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
