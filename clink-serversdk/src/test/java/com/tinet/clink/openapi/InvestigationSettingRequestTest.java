package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.config.investigation.InvestigationSettingRequest;
import com.tinet.clink.openapi.response.config.client.BindClientTelConfirmedResponse;
import com.tinet.clink.openapi.response.config.investigation.InvestigationSettingResponse;
import org.junit.Test;

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

}
