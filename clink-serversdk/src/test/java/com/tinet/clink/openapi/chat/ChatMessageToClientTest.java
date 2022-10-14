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

        configuration = new ClientConfiguration("1ad36475b5fb78c23f033250cb19a0a5", "li8FM094H5qj4O0SEdu8");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test4.clink.cn");

        client = new Client(configuration);
    }




    @Test
    public void testMessageToClient() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        ChatMessageToClientRequest request = new ChatMessageToClientRequest();
        request.setSessionId("5d43691b-7fd3-4077-b116-6ec2feb05f0f.1638415584");
        request.setMessageType(1);
        request.setContent("一样一样");

        // multipartFile 转file https://blog.csdn.net/qisoft1213/article/details/105098007
//        request.setFile(new File("F:\\钉钉缓存\\绩效管理制度配套附表-20210121(1).xlsx"));
        ChatMessageToClientResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
