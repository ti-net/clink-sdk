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
        /**
         访问秘钥ID：1ea10efeae1d50980852d5183affecac
         私有访问秘钥：470MBe590Hc8n81a0131
         */
        configuration = new ClientConfiguration("1ea10efeae1d50980852d5183affecac", "470MBe590Hc8n81a0131");
        configuration.setScheme("https");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }
}
