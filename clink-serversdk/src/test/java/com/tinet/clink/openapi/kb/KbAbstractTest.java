package com.tinet.clink.openapi.kb;

import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import org.junit.Before;

/**
 * @author feizq
 * @date 2021/06/26
 **/
public class KbAbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        configuration = new ClientConfiguration("3b4afbf79cc3ad7bb8c6d831d78625de", "318MlMFg4JnTXovlJCT7");
        configuration.setScheme("http");
        configuration.setHost("clink2-openapi-dev.clink.cn");

        client = new Client(configuration);
    }
}
