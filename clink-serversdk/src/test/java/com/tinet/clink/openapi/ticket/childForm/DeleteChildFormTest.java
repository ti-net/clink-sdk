package com.tinet.clink.openapi.ticket.childForm;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.childForm.DeleteChildFormRequest;
import com.tinet.clink.ticket.response.childForm.DeleteChildFormResponse;
import org.junit.Test;


public class DeleteChildFormTest extends AbstractTest {

    @Test
    public void deleteChildForm() {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");

        Client client = new Client(configuration);

        // 创建请求的request
        DeleteChildFormRequest listChildFormRequest = new DeleteChildFormRequest();
        // 请求参数
        // 子表单id
        listChildFormRequest.setFormId(28615);
        // 子表单名称
//        listChildFormRequest.setFormName("子表单");

        try {
            // 将请求参数赋值到 request中
            DeleteChildFormResponse ticketCloseResponse = client.getResponseModel(listChildFormRequest);
            System.out.println(ticketCloseResponse.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
