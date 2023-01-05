package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.model.Field;
import com.tinet.clink.ticket.model.TicketFormModel;
import com.tinet.clink.ticket.model.TicketPrefabricatedUpdateModel;
import com.tinet.clink.ticket.model.TicketUpdateModel;
import com.tinet.clink.ticket.request.TicketPrefabricatedUpdateRequest;
import com.tinet.clink.ticket.request.TicketUpdateRequest;
import com.tinet.clink.ticket.response.TicketPrefabricatedUpdateResponse;
import com.tinet.clink.ticket.response.TicketUpdateResponse;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangli
 * @date 2023-01-04 14:09
 */
public class TicketPrefabricatedUpdateTest extends AbstractTest {

    @Test
    public void updateTicket() throws JsonProcessingException {

//        ClientConfiguration configuration = new ClientConfiguration(
//                "0e3a87b110c55fb5fccf1dfaf25973f6",          // AccessKeyId
//                "k098n2n0r03Uw9L59915");     // AccessKeySecret
//        configuration.setHost("api-bj-test0.clink.cn");
//        configuration.setScheme("http");
//        Client client = new Client(configuration);

        // 创建请求的request
        TicketPrefabricatedUpdateRequest ticketUpdateRequest = new TicketPrefabricatedUpdateRequest();

        // 请求参数
        TicketPrefabricatedUpdateModel ticketUpdateModel = new TicketPrefabricatedUpdateModel();
        ticketUpdateModel.setId(9365);

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(6232);
        ticketFormModel.setTaskId("faebee2e-8b4a-11ed-a429-32bb0ce41cf2");

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field = new Field();
        field.setId(98741);
        field.setName("cy单行文本1");
        field.setType(1);
        field.setValue("666666");

        fieldList.add(field);
        ticketFormModel.setFields(fieldList);
        ticketUpdateModel.setForm(ticketFormModel);

        List<File> fileList = new ArrayList<>();

        File file = new File("E:\\工单信息 - 副本.xlsx");

        fileList.add(file);

        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("31075", fileList);
        // 将请求参数赋值到 request中
        ticketUpdateRequest.setModel(ticketUpdateModel);
//        ticketSaveRequest.setFileMap(fileMap);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(ticketUpdateModel));
        TicketPrefabricatedUpdateResponse ticketUpdateResponse;

        try {
            ticketUpdateResponse = client.getResponseModel(ticketUpdateRequest);
            System.out.println(objectMapper.writeValueAsString(ticketUpdateResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
