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
        configuration = new ClientConfiguration("687c8d50c392659d0814c6a68baed93c", "C3Y8Q8yD0dV791w5sS0A");
        configuration.setScheme("https");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }
}
