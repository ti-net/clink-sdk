package com.tinet.clink.openapi.ticket.stat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.stat.StatTicketByClientRequest;
import com.tinet.clink.ticket.response.stat.StatTicketByClientResponse;
import org.junit.Test;

/**
 * 工单座席情况报表单元测试
 *
 * @author wangli
 * @date 2022-08-24 7:04 下午
 */
public class StatTicketByClientTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statTicketByClient() throws JsonProcessingException, ServerException, ClientException {

        StatTicketByClientRequest request = new StatTicketByClientRequest();
        request.setDate("20220822");
        StatTicketByClientResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatTicketByClient()));
    }

}
