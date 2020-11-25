package com.tinet.clink.openapi.chat;

import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import org.junit.Before;

public class ChatAbstractTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {

        configuration = new ClientConfiguration("b30cc01d53094b4c3e45454046433a21", "9ZaZE40957v01j7CK0f8");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test5.clink.cn");

        client = new Client(configuration);
    }
}
