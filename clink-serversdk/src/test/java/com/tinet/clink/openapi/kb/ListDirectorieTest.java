package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.kb.ListDirectoriesRequest;
import com.tinet.clink.openapi.response.kb.ListDirectoriesResponse;
import org.junit.Test;

/**
 * 知识库目录请求示例
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class ListDirectorieTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listDirectories() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        ListDirectoriesRequest request = new ListDirectoriesRequest();
        request.setType(0);
        // request.setName("FAQ测试");

        try {
            ListDirectoriesResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getDirectories()));
            System.out.println(response.getDirectories().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
