package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.request.kb.*;
import com.tinet.clink.openapi.response.kb.*;
import org.junit.Test;

/**
 * @author feizq
 * @date 2022/06/16
 **/
public class AnswerTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void mediaUrl() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        MediaUrlRequest request = new MediaUrlRequest();
        request.setFileKey("837d6acb29944a4bb9d68de9d0b25bd2|df0b1e440ebf4054b969004c6f68fc64UftyXrqhHDimage(1).png");


        try {
            MediaUrlResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getMediaUrl()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listAnswer() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        ListAnswerRequest request = new ListAnswerRequest();
        request.setBotId("581926");
        request.setSqId(312681);


        try {
            ListAnswerResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getAnswers()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createAnswer() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        CreateAnswerRequest request = new CreateAnswerRequest();
        request.setBotId("581926");
        request.setAnswer("接口创建添加答案啊啊啊啊");
        request.setSqId(167189);


        try {
            CreateAnswerResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getAnswer()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateAnswer() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        UpdateAnswerRequest request = new UpdateAnswerRequest();
        request.setBotId("581926");
        request.setAnswer("接口创建添加答案啊啊啊啊11");
        request.setSqId(167189);
        request.setId(201625);


        try {
            UpdateAnswerResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getResult()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteAnswer() {

        // 创建访问服务的客户端实例并初始化
        /*ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);*/

        // 创建请求的request
        DeleteAnswerRequest request = new DeleteAnswerRequest();
        request.setBotId("581926");
        request.setIds(new Integer[]{201625});

        try {
            DeleteAnswerResponse response = client.getResponseModel(request);
            System.out.println(mapper.writeValueAsString(response.getResult()));
            System.out.println(mapper.writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
