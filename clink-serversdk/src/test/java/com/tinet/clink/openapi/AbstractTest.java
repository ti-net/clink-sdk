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
        configuration = new ClientConfiguration("cd3d669dfcf62d055ba7d464dac12a56", "0415577z2f31YxS3jw2i");
        configuration.setScheme("https");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }
}
