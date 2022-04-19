package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.model.Field;
import com.tinet.clink.openapi.model.TicketFlowModel;
import com.tinet.clink.openapi.model.TicketFormModel;
import com.tinet.clink.openapi.request.ticket.TicketFlowRequest;
import com.tinet.clink.openapi.response.ticket.TicketFlowResponse;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流转工单请求示例
 *
 * @date 2020/11/17
 **/
public class TicketFlowTest extends AbstractTest {

    @Test
    public void flowTicket() {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
        "AK",          // AccessKeyId
        "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketFlowRequest ticketFlowRequest = new TicketFlowRequest();
        // 请求参数
        TicketFlowModel ticketFlowModel = new TicketFlowModel();
        ticketFlowModel.setTicketId(1839);
        ticketFlowModel.setHandlerId(158059);
        ticketFlowModel.setTaskId("9f2e5c11-b4f7-11ec-b31e-5a8af5d6432f");

        TicketFormModel form = new TicketFormModel();
        form.setId(5090);
        form.setName("lize测试流转（预制）");

        List<Field> fields = new ArrayList<>();
        Field field1 = new Field();
        field1.setId(84276);
        field1.setName("lize单行文本1");
        field1.setType(1);
        field1.setValue("11");
        fields.add(field1);

        Field field2 = new Field();
        field2.setId(84277);
        field2.setName("lize多行文本");
        field2.setType(5);
        field2.setValue("11");
        fields.add(field2);

        Field field3 = new Field();
        field3.setId(83668);
        field3.setName("0038附件");
        field3.setType(14);
        field3.setValue(null);
        fields.add(field3);

        Field field4 = new Field();
        field4.setId(83663);
        field4.setName("0038单选框");
        field4.setType(9);
        field4.setValue("单选4");
        fields.add(field4);

        form.setFields(fields);
        ticketFlowModel.setForm(form);
        System.out.println(form.toString());

        List<File> fileList = new ArrayList<>();

        File file = new File("E:\\软件清单.txt");

        fileList.add(file);
        Map<String, List<File>> fileMap = new HashMap<>();
        fileMap.put("83668", fileList);
        ticketFlowRequest.setModel(ticketFlowModel);
        ticketFlowRequest.setFileMap(fileMap);

        try {
            //将请求参数赋值到 request中
            TicketFlowResponse ticketFlowResponse = client.getResponseModel(ticketFlowRequest);
            System.out.println(ticketFlowResponse.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}