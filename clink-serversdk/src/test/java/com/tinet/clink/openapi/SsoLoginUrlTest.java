package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tinet.clink.cc.request.sso.SsoLoginUrlRequest;
import com.tinet.clink.cc.response.sso.SsoLoginUrlResponse;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
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
        //坐席
        request.setLoginType(1);
        request.setUsername("1188");
        request.setStaticUrl("mail");
        request.setSidebarDisplay(1);
        request.setTopbarDisplay(1);
//        //座席
//        request.setLoginType(1);
//        request.setUsername("0611");
        SsoLoginUrlResponse responseModel = client.getResponseModel(request);
        System.out.println(responseModel.getLoginUrl());
    }
}
