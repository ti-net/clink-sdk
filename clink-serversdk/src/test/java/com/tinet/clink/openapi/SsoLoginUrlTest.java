package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.request.chat.ChatClientWithdrawRequest;
import com.tinet.clink.openapi.request.sso.SsoLoginUrlRequest;
import com.tinet.clink.openapi.response.chat.ChatClientWithdrawResponse;
import com.tinet.clink.openapi.response.sso.SsoLoginUrlResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 客服会话开始
 */
@SuppressWarnings("Duplicates")
public class SsoLoginUrlTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("706ff5f9bbb10286dcf7545262a7d702", "IO9Fpa392A3y54375Tvu");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test5.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testClientWithdraw() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SsoLoginUrlRequest request = new SsoLoginUrlRequest();
        //管理员
        request.setLoginType(2);
        request.setUsername("wangpw");
        //座席
        request.setLoginType(1);
        request.setUsername("0611");
        SsoLoginUrlResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
