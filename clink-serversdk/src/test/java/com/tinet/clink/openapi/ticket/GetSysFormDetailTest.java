package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.ticket.GetFormDetailRequest;
import com.tinet.clink.openapi.request.ticket.GetSysFormDetailRequest;
import com.tinet.clink.openapi.response.ticket.GetFormDetailResponse;
import com.tinet.clink.openapi.response.ticket.GetSysFormDetailResponse;
import org.junit.Test;

/**
 * 表单详情查询请求示例
 *
 * @date: 2020/11/25
 **/
public class GetSysFormDetailTest extends AbstractTest {

    @Test
    public void getSysFormDetail() {

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
        "AK",          // AccessKeyId
        "SK");     // AccessKeySecret
        configuration.setHost("host");
        configuration.setScheme("http");
        Client client = new Client(configuration);


        // 创建请求request
        GetSysFormDetailRequest getSysFormDetailRequest = new GetSysFormDetailRequest();

        try {
            GetSysFormDetailResponse getSysFormDetailResponse = client.getResponseModel(getSysFormDetailRequest);
            System.out.println(getSysFormDetailResponse.getForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}