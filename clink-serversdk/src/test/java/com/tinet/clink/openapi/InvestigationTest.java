package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.cdr.ListInvestigationsRequest;
import com.tinet.clink.cc.response.cdr.ListInvestigationsResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;


import org.junit.Test;

public class InvestigationTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void TestInvestigationList() {


        ListInvestigationsRequest request = new ListInvestigationsRequest();

        try {
            ListInvestigationsResponse response = client.getResponseModel(request);

            System.out.println(mapper.writeValueAsString(response));
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}