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


        configuration = new ClientConfiguration("48401d1bec7060ff6a18c918bb4ed6c4", "9ehn0KEW5B9ee772467u");
        /*configuration.setHost("api-bj-test10.clink.cn");
        configuration.setPort(80);
        configuration.setScheme("https");*/

        configuration.setHost("localhost");
        configuration.setPort(8092);
        configuration.setScheme("https");

        client = new Client(configuration);
    }
}
