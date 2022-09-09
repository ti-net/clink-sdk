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
        configuration = new ClientConfiguration("******", "******");
        configuration.setScheme("http");
        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");

        client = new Client(configuration);
    }
}
