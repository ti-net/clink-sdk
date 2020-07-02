package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.model.QueueDetailModel;
import com.tinet.clink.openapi.model.QueueMemberModel;
import com.tinet.clink.openapi.model.QueueSearchResultModel;
import com.tinet.clink.openapi.request.config.queue.*;
import com.tinet.clink.openapi.response.config.queue.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lizy
 * @date 2018/10/25
 */
public class QueueTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createQueue() throws ServerException, ClientException {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest();
        createQueueRequest.setQno("4444");
        createQueueRequest.setName("4444");
        createQueueRequest.setChatStrategy(2);
        createQueueRequest.setChatMaxWait(12);
        createQueueRequest.setChatLocation(6);
        createQueueRequest.setSayCno(1);
        createQueueRequest.setVipSupport(1);

        //设置队列座席关系
        List<QueueMemberModel> queueMembers = new LinkedList<QueueMemberModel>();
        QueueMemberModel queueMember = new QueueMemberModel();
        queueMember.setCno("5818");
        queueMember.setPenalty(2);
        queueMembers.add(queueMember);
        createQueueRequest.setQueueMembers(queueMembers);

        CreateQueueResponse response = client.getResponseModel(createQueueRequest);

        try {
            System.out.println(mapper.writeValueAsString(response.getQueue()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateQueue() throws ServerException, ClientException {
        UpdateQueueRequest updateQueueRequest = new UpdateQueueRequest();
        updateQueueRequest.setQno("4444");
        updateQueueRequest.setName("4444");
        updateQueueRequest.setChatStrategy(2);
        updateQueueRequest.setChatMaxWait(22);
        updateQueueRequest.setChatLocation(7);

        updateQueueRequest.setSayCno(0);
        updateQueueRequest.setVipSupport(0);



        UpdateQueueResponse response = client.getResponseModel(updateQueueRequest);

        try {
            System.out.println(mapper.writeValueAsString(response.getQueue()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listQueues() throws ServerException, ClientException {
        ListQueuesRequest request = new ListQueuesRequest();
        request.setLimit(50);
        request.setOffset(0);
        ListQueuesResponse response = client.getResponseModel(request);
        List<QueueSearchResultModel> queues = response.getQueues();
        for (QueueSearchResultModel queue : queues) {
            System.out.println(queue.toString());
        }
    }

    @Test
    public void describeQueue() throws ServerException, ClientException {
        DescribeQueueRequest request = new DescribeQueueRequest();
        request.setQno("4444");
        DescribeQueueResponse response = client.getResponseModel(request);
        try {
            System.out.println(mapper.writeValueAsString(response.getQueue()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteQueue() throws ServerException, ClientException {
        DeleteQueueRequest request = new DeleteQueueRequest();
        request.setQno("4444");
        DeleteQueueResponse response = client.getResponseModel(request);

        try {
            System.out.println(mapper.writeValueAsString(response.getQno()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


}
