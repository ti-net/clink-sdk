package com.tinet.clink.openapi;

import com.tinet.clink.openapi.model.TicketHistoryForm;
import com.tinet.clink.openapi.request.ticket.GetTicketDetailRequest;
import com.tinet.clink.openapi.request.ticket.ListTicketRequest;
import com.tinet.clink.openapi.response.ticket.GetTicketDetailResponse;
import com.tinet.clink.openapi.response.ticket.ListTicketResponse;
import org.junit.Test;

import java.util.Objects;

/**
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class TicketTest extends AbstractTest{

    @Test
    public void list(){
        ListTicketRequest listTicketRequest=new ListTicketRequest();

        listTicketRequest.setStartTime("2020-05-01 00:00:00");
        listTicketRequest.setEndTime("2020-08-10 23:59:59");
        listTicketRequest.setOffset(0);
        listTicketRequest.setLimit(10);
        try {
            ListTicketResponse response=client.getResponseModel(listTicketRequest);

            System.out.println(response);

            System.out.println(111);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getTicketDeatil() {
        GetTicketDetailRequest getTicketDetailRequest = new GetTicketDetailRequest();

        getTicketDetailRequest.setId(1470);
        try {
            GetTicketDetailResponse response = client.getResponseModel(getTicketDetailRequest);

            TicketHistoryForm[] forms = response.getTicketDetail().getForms();
            if(Objects.nonNull( response.getTicketDetail()) && Objects.nonNull(response.getTicketDetail().getForms())){
                Integer formId = forms[0].getFormId();
                String formName = forms[0].getFormName();
                System.out.println(formId + "-----" + formName);

                System.out.println(111);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}