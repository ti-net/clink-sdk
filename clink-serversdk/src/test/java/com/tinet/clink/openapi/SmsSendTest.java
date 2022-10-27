package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.sms.SmsSendRequest;
import com.tinet.clink.cc.response.sms.SmsSendResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Test;

/**
 * @author houfc
 */
public class SmsSendTest extends AbstractTest {


    private ObjectMapper mapper = new ObjectMapper();
    /**
     * 短信发送测试
     */
    @Test
    public void smsSend() throws ServerException, ClientException, JsonProcessingException {


        SmsSendRequest smsSendRequest = new SmsSendRequest();
//        smsSendRequest.setCno("0611");
        smsSendRequest.setAdminId(209);
        smsSendRequest.setContent("8888888888888888888888888");
        smsSendRequest.setTel("15176019948");
        smsSendRequest.setCustomerId(3201);

        System.out.println("-------------------1---------------");
        SmsSendResponse response = client.getResponseModel(smsSendRequest);
        System.out.println("-------------------2---------------");
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }
}
