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

        configuration = new ClientConfiguration("72b71f3af1c16159b24a8c41f70e79a9", "QSH71QNg3T7qv5lJy8W7");
        configuration.setScheme("https");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }
}
