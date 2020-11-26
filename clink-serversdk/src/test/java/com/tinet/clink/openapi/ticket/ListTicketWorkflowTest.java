package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.request.ticket.ListTicketWorkflowRequest;
import com.tinet.clink.openapi.response.ticket.ListTicketWorkflowResponse;
import org.junit.Test;

/**
 * @author huwk
 * @date 2020/11/17
 **/
public class ListTicketWorkflowTest extends AbstractTest {

    @Test
    public void listTicketWorkflow() {

        ListTicketWorkflowRequest listTicketWorkflowRequest = new ListTicketWorkflowRequest();



        listTicketWorkflowRequest.setOffset(0);
        listTicketWorkflowRequest.setLimit(20);
        ListTicketWorkflowResponse ticketWorkflowResponse;

        try {
            ticketWorkflowResponse = client.getResponseModel(listTicketWorkflowRequest);
            System.out.println(ticketWorkflowResponse.getWorkflows().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
