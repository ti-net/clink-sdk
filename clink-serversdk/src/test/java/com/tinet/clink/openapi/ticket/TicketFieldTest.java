package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.ticket.request.ListFieldsRequest;
import com.tinet.clink.ticket.request.UpdateFieldPropertyRequest;
import com.tinet.clink.ticket.response.ListFieldsResponse;
import com.tinet.clink.ticket.response.UpdateFieldPropertyResponse;
import org.junit.Test;

import java.util.Arrays;

/**
 * 工单字段相关单元测试
 *
 * @author wangli
 * @date 2022-08-09 4:33 下午
 */
public class TicketFieldTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * 查询工单字段列表单元测试
     */
    @Test
    public void listFields() throws JsonProcessingException, ServerException, ClientException {

        ListFieldsRequest request = new ListFieldsRequest();
        request.setIds(Arrays.asList(83660));
        ListFieldsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getFields()));
    }


    /**
     * 查询工单字段列表单元测试
     */
    @Test
    public void updateFieldProperty() throws ServerException, ClientException {
        UpdateFieldPropertyRequest request = new UpdateFieldPropertyRequest();
        request.setId(83660);
        request.setProperty("[{\"name\":\"下拉11111\",\"selected\":0},{\"name\":\"下拉2\",\"selected\":0},{\"name\":\"下拉3\",\"selected\":0}]");
        UpdateFieldPropertyResponse response = client.getResponseModel(request);
        System.out.println(response);
    }
}
