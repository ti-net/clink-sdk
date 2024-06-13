package com.tinet.clink.openapi.ticket.childForm;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.childForm.ListChildFormRequest;
import com.tinet.clink.ticket.response.childForm.ListChildFormResponse;
import org.junit.Test;


public class ListChildFormTest extends AbstractTest {

    @Test
    public void listChildForm() {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");

        Client client = new Client(configuration);

        // 创建请求的request
        ListChildFormRequest listChildFormRequest = new ListChildFormRequest();
        // 请求参数
        // 子表单id
//        listChildFormRequest.setFormId(19575);
        // 子表单名称
//        listChildFormRequest.setFormName("子表单");
        listChildFormRequest.setOffset(0);
        listChildFormRequest.setLimit(10);

        try {
            // 将请求参数赋值到 request中
            ListChildFormResponse ticketCloseResponse = client.getResponseModel(listChildFormRequest);
            System.out.println(ticketCloseResponse.getForms());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
