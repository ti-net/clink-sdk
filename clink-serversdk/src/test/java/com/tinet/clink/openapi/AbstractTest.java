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

        configuration = new ClientConfiguration("7c09bdcbb07700ffa815b6f28dad481e", "iq5444SLU25s265S906u");
        configuration.setScheme("http");
//        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setHost("localhost");
        configuration.setPort(8092);

        client = new Client(configuration);
    }
}
