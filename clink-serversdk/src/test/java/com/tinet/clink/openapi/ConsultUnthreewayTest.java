package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.control.ConsultUnthreewayRequest;
import com.tinet.clink.cc.request.ivr.ListIvrNodesRequest;
import com.tinet.clink.cc.request.ivr.ListIvrsRequest;
import com.tinet.clink.cc.response.control.ConsultUnthreewayResponse;
import com.tinet.clink.cc.response.ivr.ListIvrNodesResponse;
import com.tinet.clink.cc.response.ivr.ListIvrsResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 语音导航设置接口测试
 *
 * @author huwk
 * @date 2018/11/15
 **/
@Ignore
public class ConsultUnthreewayTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询ivr列表单元测试
     */
    @Test
    public void test() throws ServerException, ClientException, JsonProcessingException {

        ConsultUnthreewayRequest request = new ConsultUnthreewayRequest();
        request.setCno("6667");

        ConsultUnthreewayResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getRequestId()));
    }
}
