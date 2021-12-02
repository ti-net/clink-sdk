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
        configuration = new ClientConfiguration("c65eefac8e3894d029166828cc5cc360", "N37qAmQJa0vW099m70UW");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }
}
