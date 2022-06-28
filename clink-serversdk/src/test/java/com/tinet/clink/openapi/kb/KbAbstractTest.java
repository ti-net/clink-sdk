package com.tinet.clink.openapi.kb;

import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import org.junit.Before;

/**
 * @author feizq
 * @date 2021/06/26
 **/
public class KbAbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        configuration = new ClientConfiguration("c5616b2fdf55872306c510343cb3888e", "26zum2CfO88KvHe43h14");
        configuration.setScheme("http");
        configuration.setHost("clink2-openapi-dev.clink.cn");

        client = new Client(configuration);
    }
}
