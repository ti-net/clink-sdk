package com.tinet.clink.openapi.kb;


import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
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
        configuration = new ClientConfiguration("b05d23a7ab03360fc5a6ee10d91d6be8", "rYkSt8qmf5huD5nM73X1");
        configuration.setScheme("http");
        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");

        client = new Client(configuration);
    }
}
