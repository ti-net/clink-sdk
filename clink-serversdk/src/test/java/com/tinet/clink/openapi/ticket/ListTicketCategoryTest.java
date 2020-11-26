package com.tinet.clink.openapi.ticket;

import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.request.ticket.ListTicketCategoryRequest;
import com.tinet.clink.openapi.response.ticket.ListTicketCategoryResponse;
import org.junit.Test;

/**
 * @author liuhy
 * @date: 2020/11/25
 **/
public class ListTicketCategoryTest extends AbstractTest {

    @Test
    public void listTicketCategory() {

        ListTicketCategoryRequest listTicketCategoryRequest = new ListTicketCategoryRequest();

        ListTicketCategoryResponse listTicketCategoryResponse;

        try {
            listTicketCategoryResponse = client.getResponseModel(listTicketCategoryRequest);
            System.out.println(listTicketCategoryResponse.getCategoryModels().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}