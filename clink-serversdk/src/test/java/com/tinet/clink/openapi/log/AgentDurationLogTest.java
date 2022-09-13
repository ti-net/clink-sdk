package com.tinet.clink.openapi.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.log.ListAgentDurationLogsRequest;
import com.tinet.clink.cc.response.log.ListAgentDurationLogsResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.openapi.AbstractTest;

import org.junit.Test;

/**
 * @author wangli
 * @date 2022-03-10 5:57 PM
 */
public class AgentDurationLogTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询座席工作时长日志列表单元测试
     */
    @Test
    public void listAgentDurationLogsTest() throws ServerException, ClientException, JsonProcessingException {

        ListAgentDurationLogsRequest request = new ListAgentDurationLogsRequest();
        request.setStartTime(1646755200L);
        request.setEndTime(1646841599L);
        request.setLimit(10);
        request.setOffset(0);
        ListAgentDurationLogsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getAgentDurationLogs()));
    }

}
