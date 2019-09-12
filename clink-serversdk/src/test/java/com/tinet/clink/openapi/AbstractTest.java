package com.tinet.clink.openapi;

import org.junit.Before;

/**
 * @author houfc
 */
public abstract class AbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        configuration = new ClientConfiguration("df7f5b379e01c672d3b0b23ada55e089", "h0II3baesl87VlLpKG7K");
        configuration.setHost("api-bj-test.clink.cn");
        configuration.setPort(80);
        configuration.setScheme("http");

        client = new Client(configuration);
    }
}
