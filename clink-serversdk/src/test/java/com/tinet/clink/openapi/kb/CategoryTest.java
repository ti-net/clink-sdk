package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.request.kb.CreateCategoryRequest;
import com.tinet.clink.openapi.request.kb.DeleteCategoryRequest;
import com.tinet.clink.openapi.request.kb.UpdateCategoryRequest;
import com.tinet.clink.openapi.response.kb.CreateCategoryResponse;
import com.tinet.clink.openapi.response.kb.DeleteCategoryResponse;
import com.tinet.clink.openapi.response.kb.UpdateCategoryResponse;
import org.junit.Test;

/**
 * @author feizq
 * @date 2022/06/20
 **/
public class CategoryTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createCategory() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setBotId("581926");
        request.setName("分类名称123");
        request.setParentId(0);

        try {
            CreateCategoryResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getCategory()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateCategory() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        UpdateCategoryRequest request = new UpdateCategoryRequest();
        request.setBotId("581926");
        request.setId(11024);
        request.setName("分类名称123修改");

        try {
            UpdateCategoryResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getCategory()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCategory() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        DeleteCategoryRequest request = new DeleteCategoryRequest();
        request.setBotId("581926");
        request.setId(11024);

        try {
            DeleteCategoryResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getResult()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
