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


        configuration = new ClientConfiguration("dc3b26c043995d362743fe5efdb1c3fb", "1J97178Tu15K41l1F5G4");
        configuration.setHost("api-bj-test5.clink.cn");
//        configuration.setPort(80);
        configuration.setScheme("http");

        client = new Client(configuration);
    }
}
