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

        configuration = new ClientConfiguration("210916b1ef8f33cbc93d0eb5141228a8", "5SZ19a96q528VMx2mi69");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }
}
