package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.model.TicketUpdateModel;
import com.tinet.clink.openapi.request.ticket.TicketSaveRequest;
import com.tinet.clink.openapi.request.ticket.TicketUpdateRequest;
import com.tinet.clink.openapi.response.ticket.TicketSaveResponse;
import com.tinet.clink.openapi.response.ticket.TicketUpdateResponse;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huwk
 * @date 2020/11/17
 **/
public class TicketUpdateTest extends AbstractTest {

    @Test
    public void updateTicket() {

        TicketUpdateRequest ticketUpdateRequest = new TicketUpdateRequest();

        TicketUpdateModel updateModel=new TicketUpdateModel();

        updateModel.setId(162867);

        updateModel.setTopic("你好好好好");

        ticketUpdateRequest.setModel(updateModel);
        TicketUpdateResponse ticketUpdateResponse;

        try {
            ticketUpdateResponse = client.getResponseModel(ticketUpdateRequest);
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
