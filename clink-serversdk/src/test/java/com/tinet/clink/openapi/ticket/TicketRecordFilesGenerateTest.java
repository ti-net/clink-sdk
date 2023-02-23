package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.TicketRecordFilesGenerateRequest;
import com.tinet.clink.ticket.response.TicketRecordFilesGenerateResponse;
import org.junit.Test;

/**
 * @author wangli
 * @date 2023-02-23 20:49
 */
public class TicketRecordFilesGenerateTest extends AbstractTest {

    @Test
    public void updateTicket() throws JsonProcessingException, ServerException, ClientException {

        TicketRecordFilesGenerateRequest request = new TicketRecordFilesGenerateRequest();
        request.setType(1);
        request.setDate("");
        TicketRecordFilesGenerateResponse responseModel = client.getResponseModel(request);
        System.out.println(responseModel);

    }

}
