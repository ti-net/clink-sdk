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
        configuration = new ClientConfiguration("xxx", "xxx");
        configuration.setScheme("https");
        configuration.setHost("xxx");

        client = new Client(configuration);
    }
}
