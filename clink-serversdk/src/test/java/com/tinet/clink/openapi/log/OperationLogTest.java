package com.tinet.clink.openapi.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.log.ListOperationLogsRequest;
import com.tinet.clink.cc.response.log.ListOperationLogsResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.openapi.AbstractTest;

import com.tinet.clink.core.exceptions.ServerException;

import org.junit.Test;

/**
 * @author wangli
 * @date 2022-03-09 5:18 PM
 */
public class OperationLogTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询座席操作日志列表单元测试
     */
    @Test
    public void listOperationLogsTest() throws ServerException, ClientException, JsonProcessingException {

        ListOperationLogsRequest request = new ListOperationLogsRequest();
        request.setStartTime(1646755200L);
        request.setEndTime(1646841599L);
        request.setLimit(10);
        request.setOffset(0);
        request.setUserType("2");
        ListOperationLogsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getOperationLogs()));
    }
}
