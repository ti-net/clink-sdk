package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

/**
 * @author houfc
 */
public abstract class AbstractTest {

    protected final ObjectMapper mapper = new ObjectMapper();

    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {

        configuration = new ClientConfiguration("d249b2b5db6c0279d4e6e47ad97a3913", "op7jwj1H11Xq904AHf79");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test4.clink.cn");

        client = new Client(configuration);
    }
}
