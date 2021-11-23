package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.stat.StatClientStatusRequest;
import com.tinet.clink.openapi.request.stat.StatClientWorkloadRequest;
import com.tinet.clink.openapi.request.stat.StatQueueRequest;
import com.tinet.clink.openapi.response.stat.StatClientStatusResponse;
import com.tinet.clink.openapi.response.stat.StatClientWorkloadResponse;
import com.tinet.clink.openapi.response.stat.StatQueueResponse;

import org.junit.Test;

/**
 * @author Chenjf
 * @date 2020/2/24 15:48
 **/
public class StatTest extends AbstractTest {


    ObjectMapper mapper = new ObjectMapper();

    /**
     * 座席工作量报表单元测试
     */
    @Test
    public void statClientWorkloadTest() throws ServerException, ClientException, JsonProcessingException {
        StatClientWorkloadRequest request = new StatClientWorkloadRequest();
        request.setDate("20200225");
      /*  List<String> fields = new LinkedList<String>();
        fields.add("ibTotalCount");
        request.setFields(fields);*/
        StatClientWorkloadResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatClientWorkload()));
    }


    /**
     * 队列报表单元测试
     */
    @Test
    public void statQueueTest() throws ServerException, ClientException, JsonProcessingException {

        StatQueueRequest request = new StatQueueRequest();
        request.setDate("20200225");
      /*  List<String> fields = new LinkedList<String>();
        fields.add("totalAbandonWaitDuration");
        request.setFields(fields);*/
        StatQueueResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatQueue()));
    }


    /**
     * 队列报表单元测试
     */
    @Test
    public void statClientStatusTest() throws ServerException, ClientException, JsonProcessingException {

        StatClientStatusRequest request = new StatClientStatusRequest();
        request.setDate("20200410");
      /*  List<String> fields = new LinkedList<String>();
        fields.add("totalAbandonWaitDuration");
        request.setFields(fields);*/
        StatClientStatusResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatClientStatus()));
    }
}
