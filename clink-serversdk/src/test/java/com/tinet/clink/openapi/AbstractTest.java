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
        //test0
        configuration = new ClientConfiguration("3ee29817e02c59ab4f27f55b75053ab9", "4498PzDejj8y13e2u19Q");
        configuration.setScheme("http");
        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");

        //configuration = new ClientConfiguration("a3be59fdbc4c2f8d83cb2daf1a9c2f23", "QAilo1z79FEK4SL4ig35");
        //configuration.setScheme("http");
        //configuration.setHost("clink2-openapi-dev.clink.cn");

        client = new Client(configuration);
    }
}