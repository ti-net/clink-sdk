package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.model.ChatWindowsSetting;
import com.tinet.clink.livechat.request.ChatWebSettingUpdateRequest;
import com.tinet.clink.livechat.response.ChatWebSettingUpdateResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 修改chat-web渠道配置
 */
public class ChatWebSettingUpdateTest {
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
        ChatWebSettingUpdateRequest request = new ChatWebSettingUpdateRequest();
        request.setAccessId("9ff07fd4-ff7d-457e-b7f2-356302f0f5e4");
        request.setAccessName("lj3334");
        ChatWindowsSetting windowsSetting = new ChatWindowsSetting();
        windowsSetting.setLogo("https://img2.baidu.com/it/u=1830236614,3389089205&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
        request.setWindowSetting(windowsSetting);
        ChatWebSettingUpdateResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
