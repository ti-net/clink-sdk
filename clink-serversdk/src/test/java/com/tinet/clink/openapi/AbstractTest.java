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

        configuration = new ClientConfiguration("076057cbbe2f894329d68f6603d9b8df", "nbk6eg2y0VDt7Rq98cbJ");
        configuration.setScheme("https");
        configuration.setHost("callcenter-openapi.zbom.com");

        client = new Client(configuration);
    }
}
