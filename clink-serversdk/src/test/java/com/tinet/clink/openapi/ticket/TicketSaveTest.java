package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;

import com.tinet.clink.ticket.model.*;
import com.tinet.clink.ticket.request.StagingTicketRequest;
import com.tinet.clink.ticket.request.TicketSaveRequest;
import com.tinet.clink.ticket.response.StagingTicketResponse;
import com.tinet.clink.ticket.response.TicketSaveResponse;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * 保存工单请求示例
 *
 * @date 2020/11/17
 **/
public class TicketSaveTest extends AbstractTest {

    @Test
    public void saveTicket() throws JsonProcessingException {


        ClientConfiguration configuration = new ClientConfiguration(
                "540446eaf6a1d5f5d05ffb14d088ea79",          // AccessKeyId
                "1z2T635b489B6n4644C6");     // AccessKeySecret
//        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        configuration.setHost("clink2-openapi-dev.clink.cn");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求的request
        TicketSaveRequest ticketSaveRequest = new TicketSaveRequest();

        // 请求参数
        TicketSaveModel ticketSaveModel = new TicketSaveModel();
        ticketSaveModel.setWorkflowId(2800);
        ticketSaveModel.setClose(0);
        ticketSaveModel.setCreateTime(new Date());
        ticketSaveModel.setHandlerType(0);
        ticketSaveModel.setHandlerId(48964);
        ticketSaveModel.setLevel(3);
        ticketSaveModel.setTopic("wangli_11");
        ticketSaveModel.setCreatorId(48964);

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(6232);
        ticketFormModel.setName("wangli_form");

        // 需要保存的字段集合
        List<Field> fieldList = new ArrayList<>();

        Field field = new Field();
        field.setId(437613);
        field.setName("wangli_文本1");
        field.setRequired(0);
        field.setType(1);
        field.setValue("");

//        Field field1 = new Field();
//        field1.setId(437614);
//        field1.setName("wangli_文本2");
//        field1.setRequired(0);
//        field1.setType(1);
//        field1.setValue("wangli_文本2");

        Field childField = new Field();
        childField.setId(7352);
        childField.setName("wangli_子表单");
        childField.setRequired(0);
        childField.setType(99);
        childField.setValue("aa");

        List<Field> childFieldList = new ArrayList<>();
        Field field2 = new Field();
        field2.setId(437613);
        field2.setName("wangli_文本1");
        field2.setRequired(0);
        field2.setType(1);
        field2.setValue("");
        childFieldList.add(field2);

        childField.setChildren(childFieldList);

        fieldList.add(field);
//        fieldList.add(field1);
        fieldList.add(childField);

        ticketFormModel.setFields(fieldList);
//
//
        Field sysField1 = new Field();
        sysField1.setId(102997);
        sysField1.setName("yuxr属性单行");
        sysField1.setRequired(0);
        sysField1.setType(1);
        sysField1.setValue("yuxr属性单行");
        ticketSaveModel.setForm(ticketFormModel);


        Field[] customizeSystemFields = new Field[]{sysField1};
        ticketSaveModel.setCustomizeSystemFields(customizeSystemFields);

        ticketSaveModel.setFocus(new Integer[]{});
        ticketSaveModel.setTags(new Tag[]{});
        ticketSaveModel.setRelateTicketId(new Integer[]{});
        ticketSaveModel.setStateSelected("yuxr自定义状态");

        List<File> fileList = new ArrayList<>();

        File file = new File("E:\\工单信息 - 副本.xlsx");

        fileList.add(file);

        Map<String, List<File>> fileMap = new HashMap<>();

        fileMap.put("31075", fileList);
        //将请求参数赋值到 request中
        ticketSaveRequest.setModel(ticketSaveModel);
        ticketSaveRequest.setFileMap(fileMap);

        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writeValueAsString(ticketSaveModel));
        TicketSaveResponse ticketSaveResponse;

        try {
            ticketSaveResponse = client.getResponseModel(ticketSaveRequest);
            System.out.println(objectMapper.writeValueAsString(ticketSaveResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stagingTicket() throws JsonProcessingException {


        ClientConfiguration configuration = new ClientConfiguration(
                "******",          // AccessKeyId
                "******");     // AccessKeySecret
        configuration.setHost("clink2-openapi-dev.clink.cn");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求的request
        StagingTicketRequest ticketSaveRequest = new StagingTicketRequest();

        // 请求参数
        TicketStagingModel ticketSaveModel = new TicketStagingModel();

        ticketSaveModel.setCreatorCode(4);
//        ticketSaveModel.setCreatorValue("48964");
//        ticketSaveModel.setCreatorValue("17610031036");
//        ticketSaveModel.setCreatorValue("wangli3");
        ticketSaveModel.setCreatorValue("6666");

        ticketSaveModel.setWorkflowId(2800);
        ticketSaveModel.setClose(0);
        ticketSaveModel.setCreateTime(new Date());
        ticketSaveModel.setHandlerType(0);
        ticketSaveModel.setHandlerId(48964);
        ticketSaveModel.setLevel(3);
        ticketSaveModel.setTopic("wangli测试暂存");
//        ticketSaveModel.setCreatorId(156263);

        // 需要保存的工单的表单对象
        TicketFormModel ticketFormModel = new TicketFormModel();
        ticketFormModel.setId(6232);
        ticketFormModel.setName("wangli_form");
        ticketSaveModel.setForm(ticketFormModel);


//        Field[] customizeSystemFields = new Field[]{sysField1};
//        ticketSaveModel.setCustomizeSystemFields(customizeSystemFields);

        Tag tag = new Tag();
        tag.setName("X1");
        tag.setColor(10);
        ticketSaveModel.setFocus(new Integer[]{});
        ticketSaveModel.setTags(new Tag[]{tag});
        ticketSaveModel.setRelateTicketId(new Integer[]{});
        ticketSaveModel.setStateSelected("yuxr自定义状态");

//        List<File> fileList = new ArrayList<>();
//
//        File file = new File("E:\\工单信息 - 副本.xlsx");
//
//        fileList.add(file);
//
//        Map<String, List<File>> fileMap = new HashMap<>();
//
//        fileMap.put("31075", fileList);
        //将请求参数赋值到 request中
        ticketSaveRequest.setModel(ticketSaveModel);
//        ticketSaveRequest.setFileMap(fileMap);

        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writeValueAsString(ticketSaveModel));
        StagingTicketResponse ticketSaveResponse;

        try {
            ticketSaveResponse = client.getResponseModel(ticketSaveRequest);
            System.out.println(objectMapper.writeValueAsString(ticketSaveResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
