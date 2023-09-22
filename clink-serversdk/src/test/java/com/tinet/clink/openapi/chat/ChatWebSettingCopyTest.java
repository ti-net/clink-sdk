package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.model.ChatWindowsSetting;
import com.tinet.clink.livechat.request.ChatWebSettingCopyRequest;
import com.tinet.clink.livechat.request.ChatWebSettingUpdateRequest;
import com.tinet.clink.livechat.response.ChatWebSettingCopyResponse;
import com.tinet.clink.livechat.response.ChatWebSettingUpdateResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 复制chat-web渠道配置
 */
public class ChatWebSettingCopyTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("eb4ced4fde712c89ea726a8e4bb24ca6", "p232A34m2e640vt2ro1o");
        configuration.setScheme("https");
        configuration.setHost("clink2-openapi-dev.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testUpdateWebSetting() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatWebSettingCopyRequest request = new ChatWebSettingCopyRequest();
        request.setAccessId("9ff07fd4-ff7d-457e-b7f2-356302f0f5e4");
        ChatWebSettingCopyResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
