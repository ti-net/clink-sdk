package com.tinet.clink.openapi;

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

    @Test
    public void createQueue()throws ServerException, ClientException{
        CreateQueueRequest createQueueRequest = new CreateQueueRequest();
        createQueueRequest.setQno("2002");
        createQueueRequest.setName("测试openApi");
        createQueueRequest.setChatStrategy(1);
        createQueueRequest.setChatMaxWait(12);
        createQueueRequest.setChatLocation(6);

        //设置队列座席关系
        List<QueueMemberModel> queueMembers = new LinkedList<QueueMemberModel>();
        QueueMemberModel queueMember = new QueueMemberModel();
        queueMember.setCno("5818");
        queueMember.setPenalty(2);
        queueMembers.add(queueMember);
        createQueueRequest.setQueueMembers(queueMembers);

        CreateQueueResponse response = client.getResponseModel(createQueueRequest);
        response.getQueue();
    }

    @Test
    public void updateQueue()throws ServerException, ClientException{
        UpdateQueueRequest updateQueueRequest = new UpdateQueueRequest();
        updateQueueRequest.setQno("2002");
        updateQueueRequest.setName("测试openApi11");
        updateQueueRequest.setChatStrategy(2);
        updateQueueRequest.setChatMaxWait(22);
        updateQueueRequest.setChatLocation(7);

        //修改队列座席关系
        List<QueueMemberModel> queueMembers = new LinkedList<QueueMemberModel>();
        QueueMemberModel queueMember1 = new QueueMemberModel();
        queueMember1.setCno("5818");
        queueMember1.setPenalty(2);
        queueMembers.add(queueMember1);
        QueueMemberModel queueMember2 = new QueueMemberModel();
        queueMember2.setCno("8902");
        queueMember2.setPenalty(3);
        queueMembers.add(queueMember2);
        updateQueueRequest.setQueueMembers(queueMembers);

        UpdateQueueResponse response = client.getResponseModel(updateQueueRequest);
        response.getQueue();
    }

    @Test
    public void listQueues()throws ServerException, ClientException{
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
    public void describeQueue()throws ServerException, ClientException{
        DescribeQueueRequest request = new DescribeQueueRequest();
        request.setQno("1111");
        DescribeQueueResponse response = client.getResponseModel(request);
        QueueDetailModel queue = response.getQueue();
        System.out.println(queue.toString());
    }

    @Test
    public void deleteQueue()throws ServerException, ClientException{
        DeleteQueueRequest request = new DeleteQueueRequest();
        request.setQno("2002");
        DeleteQueueResponse response = client.getResponseModel(request);
        response.getRequestId();
    }




}
