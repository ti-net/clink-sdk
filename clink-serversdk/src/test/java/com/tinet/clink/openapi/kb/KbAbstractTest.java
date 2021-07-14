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
        // 7c09bdcbb07700ffa815b6f28dad481e  iq5444SLU25s265S906u
        // 19365a76a9fccd79c637b69e2932b9db  HN39Nr1rQgr98j2W06V8
        configuration = new ClientConfiguration("7c09bdcbb07700ffa815b6f28dad481e", "iq5444SLU25s265S906u");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test0.clink.cn");

        client = new Client(configuration);
    }
}
