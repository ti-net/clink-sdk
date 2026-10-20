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
        configuration = new ClientConfiguration("7ca7a871c0b0eeb1bf356449931d3866", "Q6M35169p00893V6314T");
        configuration.setScheme("https");
        configuration.setPort(443);
        configuration.setHost("api-bj-test0.clink.cn");
        client = new Client(configuration);
    }

    @Test
    public void testCreateChannelAccess() throws Exception {
        CreateChannelAccessRequest request = new CreateChannelAccessRequest();
        request.setContactType(2);
        request.setName("官网");
        request.setIvrId(17961);
        CreateChannelAccessResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testGetChannelAccess() throws Exception {
        GetChannelAccessRequest request = new GetChannelAccessRequest();
        request.setContactType(2);
        request.setAccessId("f9dfca77-888f-41ef-9c4d-028b7b492798");
        GetChannelAccessResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testUpdateChannelAccessQueue() throws Exception {
        UpdateChannelAccessRequest request = new UpdateChannelAccessRequest();
        request.setContactType(2);
        request.setAccessId("0c1c46bc-0854-44a5-85c6-38ba9db0c3f0");
        request.setName("官方openapi");
        request.setIvrId(17965);
        UpdateChannelAccessResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testUpdateChatWebWindow() throws Exception {
        UpdateChatWebWindowRequest request = new UpdateChatWebWindowRequest();
        request.setAccessId("0c1c46bc-0854-44a5-85c6-38ba9db0c3f0");
        ChatWindowsSetting windowSetting = new ChatWindowsSetting();
        windowSetting.setTitle("在线咨询openapi");
        request.setWindowSetting(windowSetting);
        UpdateChatWebWindowResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testPreviewChatWeb() throws Exception {
        PreviewChatWebRequest request = new PreviewChatWebRequest();
        request.setAccessId("0c1c46bc-0854-44a5-85c6-38ba9db0c3f0");
        PreviewChatWebResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testDeleteChannelAccess() throws Exception {
        DeleteChannelAccessRequest request = new DeleteChannelAccessRequest();
        request.setContactType(2);
        request.setAccessId("0c1c46bc-0854-44a5-85c6-38ba9db0c3f0");
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
