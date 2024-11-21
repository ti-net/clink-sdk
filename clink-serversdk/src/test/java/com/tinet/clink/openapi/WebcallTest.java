package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.webcall.WebcallRequest;
import com.tinet.clink.cc.response.webcall.WebcallResponse;


import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * webcall接口测试
 *
 * @author wangll
 * @date 2018/10/29
 **/
@Ignore
public class WebcallTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 上线单元测试
     */
    @Test
    public void webcallTest() throws ServerException, ClientException, JsonProcessingException {

        WebcallRequest request = new WebcallRequest();
        request.setCustomerNumber("13716619459");
        request.setIvrName("aaa");
        request.expires(100);
        request.setClidGroupName("bcytest");


        WebcallResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

}
