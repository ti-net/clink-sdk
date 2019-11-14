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
        configuration = new ClientConfiguration("68531638194ed8d3ec142e317635296c", "xbly5F6yEDFmYbZSeq60");
        configuration.setHost("api-bj-test.clink.cn");
        configuration.setPort(80);
        configuration.setScheme("http");

        client = new Client(configuration);
    }
}
