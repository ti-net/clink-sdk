package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.ticket.request.ListTicketRequest;
import com.tinet.clink.ticket.response.ListTicketResponse;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ListTicketTestFin {

    @Test
    public void listTicket(){

        com.tinet.clink.core.client.ClientConfiguration configuration = new ClientConfiguration(
                "657c8b11f7ae9bc9b51ec67747fc24f5",           // AccessKeyId
                "5924A00GL81m97NB0zH9");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        configuration.setPort(443);  // 设置端口号为443
        com.tinet.clink.core.client.Client client = new Client(configuration);

        ListTicketRequest listTicketRequest = new ListTicketRequest();
        
        // 设置自定义字段
        Map<String, String> fields = new HashMap<>();
        // 这里使用示例字段ID和订单ID，实际使用时请替换为真实值
        String orderFieldId = "295512"; // 替换为实际的订单字段ID
        String orderId = " 250522818323765374150169"; // 替换为实际的订单ID
        fields.put(orderFieldId, orderId);
        listTicketRequest.setFields(fields);

        // 设置时间范围（这里设置为最近半年）
        String endTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String startTime = LocalDateTime.now().minusMonths(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        listTicketRequest.setStartTime(startTime);
        listTicketRequest.setEndTime(endTime);

        // 设置不分页
        listTicketRequest.setLimit(100);
        listTicketRequest.setOffset(0);

        try {
            ListTicketResponse response= client.getResponseModel(listTicketRequest);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(response));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
