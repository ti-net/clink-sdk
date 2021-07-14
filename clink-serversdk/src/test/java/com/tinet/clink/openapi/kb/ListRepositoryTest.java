package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.kb.ListRepositoriesRequest;
import com.tinet.clink.openapi.response.kb.ListRepositoriesResponse;
import org.junit.Test;

/**
 * 知识库列表请求示例
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class ListRepositoryTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listRepository () {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        ListRepositoriesRequest request = new ListRepositoriesRequest();
        // request.setName("FAQ测试");
        request.setType(0);

        try {
            ListRepositoriesResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getRepositories()));
            System.out.println(response.getRepositories().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
