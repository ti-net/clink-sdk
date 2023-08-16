package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.tinet.clink.cc.model.InvestigationResultModel;
import com.tinet.clink.cc.request.investigation.ListInvestigationRequest;
import com.tinet.clink.cc.request.investigation.InvestigationSettingRequest;
import com.tinet.clink.cc.response.investigation.InvestigationSettingResponse;
import com.tinet.clink.cc.response.investigation.ListInvestigationResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Test;

import java.util.List;

/**
 * @author lizy
 * @date 2018/10/23
 */
public class InvestigationSettingRequestTest extends AbstractTest{


    @Test
    public void investigationSetting() throws ServerException, ClientException, JsonProcessingException {
        InvestigationSettingRequest request = new InvestigationSettingRequest();
        InvestigationSettingResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }


    @Test
    public void listClientsTest() throws ServerException, ClientException{
        ListInvestigationRequest listInvestigationsRequest = new ListInvestigationRequest();

        listInvestigationsRequest.setOffset(0);
        listInvestigationsRequest.setLimit(10);
        ListInvestigationResponse response = client.getResponseModel(listInvestigationsRequest);
        List<InvestigationResultModel> investigations = response.getInvestigations();
        System.out.println(investigations);
    }

}
