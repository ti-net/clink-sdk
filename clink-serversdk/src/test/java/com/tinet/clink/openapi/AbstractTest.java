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
        configuration = new ClientConfiguration(
                "76836982809697349dbbcc4679fb2217",          // AccessKeyId
                "Ys00n39TztyR76Mf24xm");     // AccessKeySecret
        configuration.setHost("api-sh.clink.cn");
        client = new Client(configuration);
    }
}
