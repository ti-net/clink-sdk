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
        configuration = new ClientConfiguration("74f10f3e7976f982b82403bfcf6e72d5", "h0II3baesl87VlLpKG7K");
        configuration.setHost("api-bj-test.clink.cn");
        configuration.setPort(80);

        client = new Client(configuration);
    }
}
