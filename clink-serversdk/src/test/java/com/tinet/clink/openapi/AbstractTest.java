package com.tinet.clink.openapi;

import org.junit.Before;

/**
 * 访问秘钥ID：5a2b3ccc76d58ceca44daadbc8ea5707
 * 私有访问秘钥：48ogGS2Lo4O5A1ONp114
 *
 * @author houfc
 */
public abstract class AbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        configuration = new ClientConfiguration("5a2b3ccc76d58ceca44daadbc8ea5707", "48ogGS2Lo4O5A1ONp114");
        configuration.setHost("api-bj-test.clink.cn");
        configuration.setPort(80);
        configuration.setScheme("http");

        client = new Client(configuration);
    }
}
