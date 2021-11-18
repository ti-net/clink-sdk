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
//        configuration = new ClientConfiguration("a613380c71ef5f9c419579c549e47276", "E80KX03IQl128FdXm0Pk");
        configuration.setScheme("https");
        configuration.setHost("callcenter-openapi.zbom.com");
//        configuration.setHost("api-bj-test13.clink.cn");

        client = new Client(configuration);
    }
}
