package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.tinet.clink.cc.request.monitor.AgentStatusDetailRequest;
import com.tinet.clink.cc.response.monitor.AgentStatusDetailResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Test;

/**
 * @author libin
 * @date 2022-03-23 9:51 上午
 * 座席状态详情测试类
 */
public class AgentStatusDetailTest extends AbstractTest {

    @Test
    public void detailTest() throws ServerException, ClientException, JsonProcessingException {
        AgentStatusDetailRequest request = new AgentStatusDetailRequest();
        request.setCno("88888");
        AgentStatusDetailResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }
}
