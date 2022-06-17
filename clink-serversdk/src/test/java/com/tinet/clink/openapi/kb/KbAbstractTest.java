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
        configuration = new ClientConfiguration("aad8aa23e7d5c53181180aab0d4d404e", "1x005imcSLnB2JrO8fg4");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }
}
