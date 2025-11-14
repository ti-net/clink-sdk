package com.tinet.clink.openapi.huanxin;

import com.tinet.clink.huanxin.request.CreateQueueRequest;
import com.tinet.clink.huanxin.request.DeleteQueueRequest;
import com.tinet.clink.huanxin.request.ListQueueRequest;
import com.tinet.clink.huanxin.request.UpdateQueueRequest;
import com.tinet.clink.huanxin.response.CreateQueueResponse;
import com.tinet.clink.huanxin.response.DeleteQueueResponse;
import com.tinet.clink.huanxin.response.ListQueueResponse;
import com.tinet.clink.huanxin.response.UpdateQueueResponse;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

public class QueueTest extends AbstractTest {

    @Test
    public void createQueue() throws Exception {
        CreateQueueRequest request = new CreateQueueRequest();
        request.setQueueType("Message");
        request.setName("tj-test1");
        CreateQueueResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }

    @Test
    public void deleteQueue() throws Exception {
        DeleteQueueRequest request = new DeleteQueueRequest();
        request.setQueueType("Message");
        request.setQueueId("268650");
        request.setUserId("bf1f0e8b-420a-4c44-8766-e19bfac027a5");
        DeleteQueueResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }

    @Test
    public void updateQueue() throws Exception {
        UpdateQueueRequest request = new UpdateQueueRequest();
        request.setQueueType("Message");
        request.setQueueId("268652");
        request.setName("tj-test2");
        UpdateQueueResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }

    @Test
    public void listQueue() throws Exception {
        ListQueueRequest request = new ListQueueRequest();
        request.setQueueType("Message");
        request.setPage(0);
        request.setSize(10);
        ListQueueResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }
}
