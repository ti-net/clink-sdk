package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.kb.request.*;
import com.tinet.clink.kb.response.DeleteStandardQuestionResponse;
import com.tinet.clink.kb.response.ListStandardQuestionResponse;
import com.tinet.clink.kb.response.PageStandardQuestionResponse;
import com.tinet.clink.kb.response.StandardQuestionResponse;
import org.junit.Test;

import java.time.Instant;

/**
 * @author feizq
 * @date 2022/06/16
 **/
public class StandardQuestionTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void pageStandardQuestion() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        PageStandardQuestionRequest request = new PageStandardQuestionRequest();
        request.setBotId("967209");
//        request.setUpdateTime("1999/01/01 00:00:00");
        request.setOffset(0);
        request.setLimit(10);

        long start = Instant.now().getEpochSecond();
        try {
            PageStandardQuestionResponse response = client.getResponseModel(request);
            long end = Instant.now().getEpochSecond();
            System.out.println("请求时长：" + (end - start));
//            System.out.println(response.getStandardQuestions().size());
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            long end = Instant.now().getEpochSecond();
            System.out.println("请求时长：" + (end - start));
            e.printStackTrace();
        }
    }

    @Test
    public void listStandardQuestion() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        ListStandardQuestionRequest request = new ListStandardQuestionRequest();
        request.setBotId("996712");

        long start = Instant.now().getEpochSecond();
        try {
            ListStandardQuestionResponse response = client.getResponseModel(request);
            long end = Instant.now().getEpochSecond();
            System.out.println("请求时长：" + (end - start));
            System.out.println(response.getStandardQuestions().size());
//            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            long end = Instant.now().getEpochSecond();
            System.out.println("请求时长：" + (end - start));
            e.printStackTrace();
        }
    }

    @Test
    public void createStandardQuestion() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        CreateStandardQuestionRequest request = new CreateStandardQuestionRequest();
        request.setBotId("581926");
        request.setTitle("平台调用创建标准问2");
        request.setCategoryId(-1);

        try {
            StandardQuestionResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getStandardQuestion()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStandardQuestion() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        UpdateStandardQuestionRequest request = new UpdateStandardQuestionRequest();
        request.setBotId("581926");
        request.setTitle("平台调用创建标准问234");
        request.setId(167189);
        request.setCategoryId(-1);

        try {
            StandardQuestionResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getStandardQuestion()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteStandardQuestion() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        DeleteStandardQuestionRequest request = new DeleteStandardQuestionRequest();
        request.setBotId("581926");
        request.setIds(new Integer[]{167189});

        try {
            DeleteStandardQuestionResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getResult()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
