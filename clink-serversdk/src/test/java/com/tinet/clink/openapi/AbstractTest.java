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

        configuration = new ClientConfiguration("afe36e010abf17b88c673ebc87f827bb", "X7I136a86K895T0E7e28");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test5.clink.cn");

        client = new Client(configuration);
    }
}
