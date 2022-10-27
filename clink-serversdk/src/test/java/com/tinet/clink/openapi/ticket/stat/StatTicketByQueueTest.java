package com.tinet.clink.openapi.ticket.stat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.stat.StatTicketByQueueRequest;
import com.tinet.clink.ticket.response.stat.StatTicketByQueueResponse;
import org.junit.Test;

/**
 * 工单座席组情况报表单元测试
 *
 * @author wangli
 * @date 2022-08-24 7:05 下午
 */
public class StatTicketByQueueTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statTicketByQueue() throws JsonProcessingException, ServerException, ClientException {

        StatTicketByQueueRequest request = new StatTicketByQueueRequest();
        request.setDate("20220822");
        StatTicketByQueueResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatTicketByQueue()));
    }

}
