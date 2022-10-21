package com.tinet.clink.openapi.ticket.stat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.stat.StatTicketRequest;
import com.tinet.clink.ticket.response.stat.StatTicketResponse;
import org.junit.Test;



/**
 * 工单整体报表单元测试
 *
 * @author wangli
 * @date 2022-08-24 6:54 下午
 */
public class StatTicketTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statTicket() throws JsonProcessingException, ServerException, ClientException {

        StatTicketRequest request = new StatTicketRequest();
        request.setDate("20220822");
        StatTicketResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatTicket()));
    }

}
