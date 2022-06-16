package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.request.kb.CreateAnswerRequest;
import com.tinet.clink.openapi.request.kb.DeleteAnswerRequest;
import com.tinet.clink.openapi.request.kb.UpdateAnswerRequest;
import com.tinet.clink.openapi.response.kb.CreateAnswerResponse;
import com.tinet.clink.openapi.response.kb.DeleteAnswerResponse;
import com.tinet.clink.openapi.response.kb.UpdateAnswerResponse;
import org.junit.Test;

/**
 * @author feizq
 * @date 2022/06/16
 **/
public class AnswerTest extends KbAbstractTest{

    private final ObjectMapper mapper = new ObjectMapper();

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
