package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;

import com.tinet.clink.ticket.model.Field;
import com.tinet.clink.ticket.model.TicketFlowModel;
import com.tinet.clink.ticket.model.TicketFormModel;
import com.tinet.clink.ticket.request.TicketFlowRequest;
import com.tinet.clink.ticket.response.TicketFlowResponse;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * 流转工单请求示例
 *
 * @date 2020/11/17
 **/
public class TicketFlowTest extends AbstractTest {

    @Test
    public void flowTicket() throws JsonProcessingException {
        ClientConfiguration configuration = new ClientConfiguration(
                "7ca7a871c0b0eeb1bf356449931d3866",          // AccessKeyId
                "Q6M35169p00893V6314T");     // AccessKeySecret
//        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketFlowRequest ticketFlowRequest = new TicketFlowRequest();
        // 请求参数
        TicketFlowModel ticketFlowModel = new TicketFlowModel();
        ticketFlowModel.setTicketId(74382);
        ticketFlowModel.setHandlerId("051204");
        ticketFlowModel.setHandlerIdType(4);
        ticketFlowModel.setNextHandlerId("1034111");
        ticketFlowModel.setNextHandlerIdType(4);
        ticketFlowModel.setTaskId("53d8ec27-d4a6-11f0-83f1-0e6a5c04debb");

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(25960);
        ticketFormModel.setTaskId("StartEvent_1uwp14d-907255ce-05b0-4222-b975-63036e515504");
        ticketFormModel.setName("liuxl_全表单_自增表格_字段同步");

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field1 = new Field();
        field1.setId(265721);
        field1.setValue("23423rder");
        field1.setName("yuxr邮箱-SC");
        field1.setType(3);



        Field field2 = new Field();
        field2.setId(265943);
        field2.setValue("(+86)17602810001");
        field2.setName("yuxr电话代码");
        field2.setType(100);

        Field field3 = new Field();
        field3.setId(265746);
        field3.setValue("广东省");
        field3.setName("yuxr级联多选-SC");
        field3.setType(18);

        Field field4 = new Field();
        field4.setId(7081);
        field4.setName("yuxr子表单_SC");
        field4.setType(99);
        field4.setChildren(new ArrayList<>());

        Field childField51 = new Field();
        childField51.setId(173297);
        childField51.setName("yuxr列表检索-SC");
        childField51.setType(109);
        Field field5 = new Field();
        field5.setId(25262);
        field5.setName("liuxl子表单_级联选择");
        field5.setType(99);
        field5.setValue("A,A1,A2,A3,A4");
        field5.setChildren(Arrays.asList(childField51));


        Field childField61 = new Field();
        childField61.setId(265941);
        childField61.setName("yuxr附件-SC");
        childField61.setType(14);
        Field childField62 = new Field();
        childField62.setId(120569);
        childField62.setName("yuxr复选-SC");
        childField62.setType(10);
        Field childField63 = new Field();
        childField63.setId(120565);
        childField63.setValue("下拉2-改");
        childField63.setName("yuxr下拉-SC");
        childField63.setType(6);
        Field field6 = new Field();
        field6.setId(25563);
        field6.setType(99);
        field6.setName("liuxl__子表单下拉_自增表");
        field6.setValue("A类liuxl自增表格全名称二十个字左右");
        field6.setChildren(Arrays.asList(childField61, childField62, childField63));

        Field field7 = new Field();
        field7.setId(31621);
        field7.setType(99);
        field7.setName("孔其富文本子表单");
        field7.setChildren(new ArrayList<>());

        Field field8 = new Field();
        field8.setId(265941);
        field8.setName("yuxr附件-SC");
        field8.setType(14);

        fieldList.addAll(Arrays.asList(field1, field2, field3, field4, field5, field6, field7, field8));
        //fieldList.addAll(Arrays.asList(field1, field2, field3));

        ticketFormModel.setFields(fieldList);


        Field sysField = new Field();
        sysField.setId(298916);
        sysField.setValue("选项27");
        sysField.setType(6);

        ticketFlowModel.setForm(ticketFormModel);
        Field[] customizeSystemFields = new Field[]{sysField};
        ticketFlowModel.setCustomizeSystemFields(customizeSystemFields);


        List<File> fileList = new ArrayList<>();

        File file = new File("/Users/caowenyi/Downloads/工单列表导出_20251127_155259.xlsx");

        fileList.add(file);

        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("265941", fileList);
        fileMap.put("25563A类liuxl自增表格全名称二十个字左右265941", fileList);

        ticketFlowRequest.setModel(ticketFlowModel);
        ticketFlowRequest.setFileMap(fileMap);

        System.out.println(mapper.writeValueAsString(ticketFlowModel));

        try {
            //将请求参数赋值到 request中
            TicketFlowResponse ticketFlowResponse = client.getResponseModel(ticketFlowRequest);
            System.out.println(ticketFlowResponse.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
