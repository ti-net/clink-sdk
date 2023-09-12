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
        configuration = new ClientConfiguration("c58cce1ab1b0ce2164d6c757ccd03306", "94OmWluW56dpV05iy7Zx");
        configuration.setScheme("https");
        configuration.setHost("api-sh.clink.cn");

        client = new Client(configuration);
    }
}
