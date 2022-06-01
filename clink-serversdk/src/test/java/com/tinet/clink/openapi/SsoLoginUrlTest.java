package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.request.chat.ChatClientWithdrawRequest;
import com.tinet.clink.openapi.request.sso.SsoLoginUrlRequest;
import com.tinet.clink.openapi.response.chat.ChatClientWithdrawResponse;
import com.tinet.clink.openapi.response.sso.SsoLoginUrlResponse;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("Duplicates")
public class SsoLoginUrlTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("627c7ce009d4210f2ba56969660e17e3", "65Z1Os89DWZ062U809D2");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testSsoLoginUrl() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SsoLoginUrlRequest request = new SsoLoginUrlRequest();
        //管理员
        request.setLoginType(1);
        request.setUsername("1188");
        request.setStaticUrl("mail");
//        //座席
//        request.setLoginType(1);
//        request.setUsername("0611");
        SsoLoginUrlResponse responseModel = client.getResponseModel(request);
        System.out.println(responseModel.getLoginUrl());
    }
}
