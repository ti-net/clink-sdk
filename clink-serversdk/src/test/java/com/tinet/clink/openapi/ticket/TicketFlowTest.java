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
        ClientConfiguration configuration = new ClientConfiguration(
                "a307c59f2e9e95b236aaded45806366c",          // AccessKeyId
                "55xWk0s47070pZK82e0w");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketFlowRequest ticketFlowRequest = new TicketFlowRequest();
        // 请求参数
        TicketFlowModel ticketFlowModel = new TicketFlowModel();
        ticketFlowModel.setTicketId(171360);
        ticketFlowModel.setHandlerId(156263);
        ticketFlowModel.setNextHandlerId(162689);
        ticketFlowModel.setTaskId("cfe34066-037f-11ed-8b90-8a9093819d2f");

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(5881);
        ticketFormModel.setName("lize测接口");

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field = new Field();
        field.setId(83466);
        field.setName("lize测多行文本");
        field.setRequired(0);
        field.setType(5);
        field.setValue("1234");

        Field field1 = new Field();
        field1.setId(85482);
        field1.setName("lize测单行文本");
        field1.setRequired(0);
        field1.setType(1);
        field1.setValue("4321");

        Field childField = new Field();
        childField.setId(4483);
        childField.setName("lize测试子表单");
        childField.setRequired(0);
        childField.setType(99);
        childField.setValue("lize子表单分类1");

        List<Field> childFieldList = new ArrayList<>();
        Field field2 = new Field();
        field2.setId(27164);
        field2.setName("yy单选框");
        field2.setRequired(0);
        field2.setType(9);
        field2.setValue("1234");
        childFieldList.add(field2);

        childField.setChildren(childFieldList);

        fieldList.add(field);
        fieldList.add(field1);
        fieldList.add(childField);

        ticketFormModel.setFields(fieldList);


        Field sysField1 = new Field();
        sysField1.setId(86686);
        sysField1.setName("lize自定义属性");
        sysField1.setRequired(0);
        sysField1.setType(1);
        sysField1.setValue("12111134");
        ticketFlowModel.setForm(ticketFormModel);


        Field[] customizeSystemFields = new Field[]{sysField1};
        ticketFlowModel.setCustomizeSystemFields(customizeSystemFields);

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
