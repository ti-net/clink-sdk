package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
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
        configuration = new ClientConfiguration("9734e2c05481be4208e8393a102322fd", "32gnPiBNWX9RWkLAy993");
        configuration.setScheme("https");
        configuration.setHost("api-bj-test0.clink.cn");
        client = new Client(configuration);
    }
}
