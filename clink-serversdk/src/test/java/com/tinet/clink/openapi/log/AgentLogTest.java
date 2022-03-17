package com.tinet.clink.openapi.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.log.ListAgentLogsRequest;
import com.tinet.clink.openapi.response.log.ListAgentLogsResponse;
import org.junit.Test;

/**
 * @author wangli
 * @date 2022-03-09 6:04 PM
 */
public class AgentLogTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询座席工作日志列表单元测试
     */
    @Test
    public void listAgentLogsTest() throws ServerException, ClientException, JsonProcessingException {

        ListAgentLogsRequest request = new ListAgentLogsRequest();
        request.setStartTime(1646755200L);
        request.setEndTime(1646841599L);
        request.setLimit(10);
        request.setOffset(0);
        ListAgentLogsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getAgentLogs()));
    }

}
