package com.tinet.clink.openapi.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.log.ListAgentLogsRequest;
import com.tinet.clink.openapi.request.log.ListLoginLogsRequest;
import com.tinet.clink.openapi.response.log.ListAgentLogsResponse;
import com.tinet.clink.openapi.response.log.ListLoginLogsResponse;
import org.junit.Test;

/**
 * @author wangli
 * @date 2022-03-10 5:54 PM
 */
public class LoginLogTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询登录日志列表单元测试
     */
    @Test
    public void listLoginLogsTest() throws ServerException, ClientException, JsonProcessingException {

        ListLoginLogsRequest request = new ListLoginLogsRequest();
        request.setUserType("2");
        request.setStartTime(1646841600L);
        request.setEndTime(1646927999L);
        request.setLimit(10);
        request.setOffset(0);
        ListLoginLogsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getLoginLogs()));
    }

}
