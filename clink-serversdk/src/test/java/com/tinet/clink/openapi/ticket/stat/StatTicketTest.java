package com.tinet.clink.openapi.ticket.stat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.stat.StatTicketRequest;
import com.tinet.clink.ticket.response.stat.StatTicketResponse;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 工单整体报表单元测试
 *
 * @author wangli
 * @date 2022-08-24 6:54 下午
 */
public class StatTicketTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statTicket() throws JsonProcessingException, ServerException, ClientException {

        StatTicketRequest request = new StatTicketRequest();
        request.setDate("20251022");

        Map<String,String> fieldMap = new HashMap<String,String>();
        fieldMap.put("299092", "下拉1");
        request.setFieldMap(fieldMap);

        Map<String,String> systemFieldMap = new HashMap<String,String>();
        fieldMap.put("299100", "层级1,层级1-2");
        request.setSystemFieldMap(fieldMap);


        List<Integer> levels = new ArrayList<>();
        levels.add(1);
        levels.add(2);
        levels.add(3);
        levels.add(0);
        request.setLevels(levels);

        List<Integer> workflowIds = new ArrayList<>();
        workflowIds.add(18662);
        workflowIds.add(18663);
        request.setWorkflowIds(workflowIds);

        StatTicketResponse response = client.getResponseModel(request, ContentType.APPLICATION_FORM_URLENCODED);
        System.out.println(mapper.writeValueAsString(response.getStatTicket()));
    }

}
