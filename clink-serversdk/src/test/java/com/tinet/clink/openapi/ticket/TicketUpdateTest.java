package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.model.*;
import com.tinet.clink.ticket.model.TicketUpdateModel;
import com.tinet.clink.ticket.request.TicketPrefabricatedUpdateRequest;
import com.tinet.clink.ticket.request.TicketSaveRequest;
import com.tinet.clink.ticket.request.TicketUpdateRequest;
import com.tinet.clink.ticket.response.TicketPrefabricatedUpdateResponse;
import com.tinet.clink.ticket.response.TicketSaveResponse;
import com.tinet.clink.ticket.response.TicketUpdateResponse;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * 工单更新请求示例
 *
 * @date 2020/11/17
 **/
public class TicketUpdateTest extends AbstractTest {

    @Test
    public void updateWorkflowTicket() throws JsonProcessingException {

        ClientConfiguration configuration = new ClientConfiguration(
                "7ca7a871c0b0eeb1bf356449931d3866",          // AccessKeyId
                "Q6M35169p00893V6314T");     // AccessKeySecret
//        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketUpdateRequest ticketUpdateRequest = new TicketUpdateRequest();

        // 请求参数
        TicketUpdateModel ticketUpdateModel = new TicketUpdateModel();
        ticketUpdateModel.setId(74361);

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

        Field field2 = new Field();
        field2.setId(265943);
        field2.setValue("(+86)17602810001");

        Field field3 = new Field();
        field3.setId(265746);
        field3.setValue("广东省");

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

        ticketUpdateModel.setForm(ticketFormModel);
        Field[] customizeSystemFields = new Field[]{sysField};
        ticketUpdateModel.setCustomizeSystemFields(customizeSystemFields);


        List<File> fileList = new ArrayList<>();

        File file = new File("/Users/caowenyi/Downloads/工单列表导出_20251127_155259.xlsx");

        fileList.add(file);

        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("265941", fileList);
        fileMap.put("25563A类liuxl自增表格全名称二十个字左右265941", fileList);

        ticketUpdateRequest.setModel(ticketUpdateModel);
        ticketUpdateRequest.setFileMap(fileMap);

        System.out.println(mapper.writeValueAsString(ticketUpdateModel));
        TicketUpdateResponse ticketUpdateResponse;

        try {
            ticketUpdateRequest.expires(24 * 3600);
            ticketUpdateResponse = client.getResponseModel(ticketUpdateRequest);
            System.out.println(mapper.writeValueAsString(ticketUpdateResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void updateArtificialTicket() throws JsonProcessingException {


        ClientConfiguration configuration = new ClientConfiguration(
                "7ca7a871c0b0eeb1bf356449931d3866",          // AccessKeyId
                "Q6M35169p00893V6314T");     // AccessKeySecret
//        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketUpdateRequest ticketUpdateRequest = new TicketUpdateRequest();

        // 预制系统属性字段
        TicketUpdateModel ticketUpdateModel = new TicketUpdateModel();
        ticketUpdateModel.setId(74386);
        ticketUpdateModel.setTopic("接口提示错误");


        // 工单表单
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(25960);
        ticketFormModel.setName("liuxl_全表单_自增表格_字段同步");

        // 工单自定义字段
        List<Field> fieldList = new ArrayList<>();

        Field field1 = new Field();
        field1.setId(265721);
        field1.setValue("23423rder");

        Field field2 = new Field();
        field2.setId(265943);
        field2.setValue("(+86)17602810001");

        Field field3 = new Field();
        field3.setId(265746);
        field3.setValue("广东省");

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

        ticketFormModel.setFields(fieldList);
        ticketUpdateModel.setForm(ticketFormModel);

        List<File> fileList = new ArrayList<>();

        File file = new File("/Users/caowenyi/Downloads/工单列表导出_20251127_155259.xlsx");

        fileList.add(file);

        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("265941", fileList);
        fileMap.put("25563A类liuxl自增表格全名称二十个字左右265941", fileList);
        ticketUpdateRequest.setFileMap(fileMap);

        ticketUpdateRequest.setModel(ticketUpdateModel);


        //ticketUpdateModel.setHandlerId(1001612);
        //ticketUpdateModel.setHandlerType(0);
        ticketUpdateModel.setStateSelected("处理中默认111_红色");

        //ticketUpdateModel.setCreatorId(1001612);

        // 工单自定义系统属性
        Field sysField = new Field();
        sysField.setId(298916);
        sysField.setValue("选项3");
        sysField.setType(6);
        Field[] customizeSystemFields = new Field[]{sysField};
        ticketUpdateModel.setCustomizeSystemFields(customizeSystemFields);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(mapper.writeValueAsString(ticketUpdateModel));
        ticketUpdateRequest.setModel(ticketUpdateModel);


        TicketUpdateResponse ticketUpdateResponse;

        try {
            ticketUpdateRequest.expires(24 * 3600);
            ticketUpdateResponse = client.getResponseModel(ticketUpdateRequest);
            System.out.println(mapper.writeValueAsString(ticketUpdateResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePrefabricatedWorkflowTicket() throws JsonProcessingException {

        ClientConfiguration configuration = new ClientConfiguration(
                "7ca7a871c0b0eeb1bf356449931d3866",          // AccessKeyId
                "Q6M35169p00893V6314T");     // AccessKeySecret
//        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketPrefabricatedUpdateRequest ticketUpdateRequest = new TicketPrefabricatedUpdateRequest();

        // 请求参数
        TicketPrefabricatedUpdateModel ticketPrefabricatedUpdateModel = new TicketPrefabricatedUpdateModel();
        ticketPrefabricatedUpdateModel.setId(74366);

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(25960);
        ticketFormModel.setTaskId("StartEvent_1uwp14d-427df25b-bf79-4903-868e-6b27a6398471");
        ticketFormModel.setName("liuxl_全表单_自增表格_字段同步");

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field1 = new Field();
        field1.setId(265721);
        field1.setValue("23423rder");

        Field field2 = new Field();
        field2.setId(265943);
        field2.setValue("(+86)17602810001");

        Field field3 = new Field();
        field3.setId(265746);
        field3.setValue("广东省");

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
        ticketPrefabricatedUpdateModel.setForm(ticketFormModel);


        List<File> fileList = new ArrayList<>();

        File file = new File("/Users/caowenyi/Downloads/工单列表导出_20251127_155259.xlsx");

        fileList.add(file);

        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("265941", fileList);
        fileMap.put("25563A类liuxl自增表格全名称二十个字左右265941", fileList);

        ticketUpdateRequest.setModel(ticketPrefabricatedUpdateModel);
        ticketUpdateRequest.setFileMap(fileMap);

        System.out.println(mapper.writeValueAsString(ticketPrefabricatedUpdateModel));
        TicketPrefabricatedUpdateResponse ticketPrefabricatedUpdateResponse;

        try {
            ticketUpdateRequest.expires(24 * 3600);
            ticketPrefabricatedUpdateResponse = client.getResponseModel(ticketUpdateRequest);
            System.out.println(mapper.writeValueAsString(ticketPrefabricatedUpdateResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
