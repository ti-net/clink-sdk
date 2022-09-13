package com.tinet.clink.openapi;

import com.tinet.clink.cc.model.ClientTelModel;
import com.tinet.clink.cc.request.client.ListClientTelsRequest;
import com.tinet.clink.cc.response.client.ListClientTelsResponse;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;


/**
 * @author Wangyl
 * @date 2019/7/22
 */
public class ListClientTelsTest {
    public static void main(String[] args) {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "1595f9859363a7f0617ae8553a0728bd",          // AccessKeyId
                "9f5L5L4I7609iGF250y2");     // AccessKeySecret

        configuration.setHost("api-bj-test.clink.cn");
        Client client = new Client(configuration);

        // 创建API请求并设置参数
        ListClientTelsRequest request = new ListClientTelsRequest();
        request.setCno("5221");

        try {
            ListClientTelsResponse response = client.getResponseModel(request);
            for (ClientTelModel model : response.getClientTels()) {
                System.out.println(model.getTel());
            }

        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}
