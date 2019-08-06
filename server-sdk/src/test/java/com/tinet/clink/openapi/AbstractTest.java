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
        configuration = new ClientConfiguration("bf85ed686cefb1e953f8590dda65a018", "y7W1499078U9cY0L1AC1");
        configuration.setHost("api-bj-test1.clink.cn");
        configuration.setPort(80);

        client = new Client(configuration);
    }
}
