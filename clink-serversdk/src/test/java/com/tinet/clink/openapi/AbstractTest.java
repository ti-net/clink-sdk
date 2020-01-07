package com.tinet.clink.openapi;

import org.junit.Before;

/**
 *
 * @author houfc
 */
public abstract class AbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        configuration = new ClientConfiguration("1cda28b6368c8a50b2f31d2a82e65415", "wHH0C2YF5xvU4c447734");
        configuration.setHost("api-bj-test5.clink.cn");
//        configuration.setPort(80);
        configuration.setScheme("http");

        client = new Client(configuration);
    }
}
