package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.model.ChatWindowsSetting;
import com.tinet.clink.livechat.request.CreateChannelAccessRequest;
import com.tinet.clink.livechat.request.DeleteChannelAccessRequest;
import com.tinet.clink.livechat.request.GetChannelAccessRequest;
import com.tinet.clink.livechat.request.GetQwkfQrStatusRequest;
import com.tinet.clink.livechat.request.PreviewChatWebRequest;
import com.tinet.clink.livechat.request.UpdateChannelAccessRequest;
import com.tinet.clink.livechat.request.UpdateChatWebWindowRequest;
import com.tinet.clink.livechat.response.CreateChannelAccessResponse;
import com.tinet.clink.livechat.response.DeleteChannelAccessResponse;
import com.tinet.clink.livechat.response.GetChannelAccessResponse;
import com.tinet.clink.livechat.response.GetQwkfQrStatusResponse;
import com.tinet.clink.livechat.response.PreviewChatWebResponse;
import com.tinet.clink.livechat.response.UpdateChannelAccessResponse;
import com.tinet.clink.livechat.response.UpdateChatWebWindowResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 渠道接入 OpenAPI 测试
 */
public class ChatChannelAccessTest {

    protected Client client = null;
    ClientConfiguration configuration = null;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        configuration = new ClientConfiguration("eb4ced4fde712c89ea726a8e4bb24ca6", "p232A34m2e640vt2ro1o");
        configuration.setScheme("https");
        configuration.setHost("clink2-openapi-dev.clink.cn");
        client = new Client(configuration);
    }

    @Test
    public void testCreateChannelAccess() throws Exception {
        CreateChannelAccessRequest request = new CreateChannelAccessRequest();
        request.setContactType(2);
        request.setName("官网");
        request.setIvrId(7);
        CreateChannelAccessResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testGetChannelAccess() throws Exception {
        GetChannelAccessRequest request = new GetChannelAccessRequest();
        request.setContactType(2);
        request.setAccessId("acc");
        GetChannelAccessResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testUpdateChannelAccessQueue() throws Exception {
        UpdateChannelAccessRequest request = new UpdateChannelAccessRequest();
        request.setContactType(2);
        request.setAccessId("acc");
        request.setBlock("queue");
        Map<String, Object> setting = new HashMap<String, Object>();
        setting.put("customize", 1);
        request.setSetting(setting);
        UpdateChannelAccessResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testUpdateChatWebWindow() throws Exception {
        UpdateChatWebWindowRequest request = new UpdateChatWebWindowRequest();
        request.setAccessId("acc");
        ChatWindowsSetting windowSetting = new ChatWindowsSetting();
        windowSetting.setTitle("在线咨询");
        request.setWindowSetting(windowSetting);
        UpdateChatWebWindowResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testPreviewChatWeb() throws Exception {
        PreviewChatWebRequest request = new PreviewChatWebRequest();
        request.setAccessId("acc");
        PreviewChatWebResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testDeleteChannelAccess() throws Exception {
        DeleteChannelAccessRequest request = new DeleteChannelAccessRequest();
        request.setContactType(2);
        request.setAccessId("acc");
        DeleteChannelAccessResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testCreateFeishuChannelAccess() throws Exception {
        CreateChannelAccessRequest request = new CreateChannelAccessRequest();
        request.setContactType(13);
        request.setAccessId("cli_xxx");
        request.setName("飞书客服");
        request.setIvrId(7);
        Map<String, Object> extension = new HashMap<String, Object>();
        extension.put("appSecret", "secret");
        request.setExtension(extension);
        CreateChannelAccessResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testGetQwkfQrStatus() throws Exception {
        GetQwkfQrStatusRequest request = new GetQwkfQrStatusRequest();
        request.setTraceId("1-uuid");
        GetQwkfQrStatusResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }
}
