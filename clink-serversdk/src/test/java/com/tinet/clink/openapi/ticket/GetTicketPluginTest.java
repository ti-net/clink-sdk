package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.QueryTicketPluginRequest;
import com.tinet.clink.ticket.response.QueryTicketPluginResponse;
import org.junit.Test;

/**
 * @Description
 * @Author LinYang
 * @Date 2022/7/26
 * @Version 1.0
 **/
public class GetTicketPluginTest extends AbstractTest {

    @Test
    public void getTicketPlugin() {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "10863920e17cf91cd0e0ccdcf0b9a047",          // AccessKeyId
                "8CEn725aQw5t3l0513JN");     // AccessKeySecret
        configuration.setHost("clink2-openapi-dev.clink.cn");

        Client client = new Client(configuration);

        QueryTicketPluginRequest request = new QueryTicketPluginRequest();
//        request.setEnterpriseId(8000376);
//        request.setId(161);
        request.setExtParam("测试插件123abc");
        try {
            QueryTicketPluginResponse responseModel = client.getResponseModel(request);
            String string = new ObjectMapper().writeValueAsString(responseModel.getTicketPlugin());
            System.out.println(string);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
